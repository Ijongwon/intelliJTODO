package org.iclass.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.dao.TodoMapper;
import org.iclass.dto.PageRequestDTO;
import org.iclass.dto.PageResponseDTO;
import org.iclass.dto.TodoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoServicempl implements TodoService{
    private final TodoMapper dao;

    @Override
    public int register(TodoDto todoDto){
        log.info(">>>>>>> todoDto : {}", todoDto);
        return dao.insert(todoDto);
    }

    @Override
    public List<TodoDto> getAll() {
        return dao.selectAll();
    }

    @Override
    public TodoDto getOne(long tno) {
        return dao.selectOne(tno);
    }

    @Override
    public void remove(long tno) {
            dao.delete(tno);
    }

    @Override
    public int modify(TodoDto todoDto) {
        return dao.update(todoDto);
    }

    @Override
    public long getCount() {
        return dao.getCount();
    }

    @Override
    public PageResponseDTO<TodoDto> getList(PageRequestDTO pageDTO) {
        List<TodoDto> list = dao.selectList(pageDTO);
        long total = dao.getCount();

        PageResponseDTO<TodoDto> pageResponseDTO = PageResponseDTO.<TodoDto>withAll()
                        .pageRequestDTO(pageDTO)
                        .list(list)
                        .total(total)
                        .build();
        return pageResponseDTO;
    }


}

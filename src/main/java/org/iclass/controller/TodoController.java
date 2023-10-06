package org.iclass.controller;


import lombok.RequiredArgsConstructor;
import org.iclass.dto.PageRequestDTO;
import org.iclass.dto.TodoDto;
import org.iclass.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;


    @GetMapping("/register")
    public void register(){}


    @PostMapping("/register")
    public String registerAction(TodoDto dto){
        todoService.register(dto);

        return "redirect:/todo/list";
    }



    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, BindingResult bindingResult ,Model model){
        if(bindingResult.hasErrors()){
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        pageRequestDTO = PageRequestDTO.of(pageRequestDTO.getPage(), pageRequestDTO.getSize());
        model.addAttribute("responseDto",todoService.getList(pageRequestDTO));
    }

    @GetMapping({"/read","modify"})
    public void read(long tno,PageRequestDTO pageRequestDTO,Model model){
        TodoDto dto = todoService.getOne(tno);
        model.addAttribute("dto",dto);
    }


    @PostMapping("/modify")
    public String modifyAction(@Valid TodoDto dto,PageRequestDTO pageRequestDTO,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("tno",dto.getTno());
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }

        todoService.modify(dto);

        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());

        return "redirect:/todo/list";
    }

}

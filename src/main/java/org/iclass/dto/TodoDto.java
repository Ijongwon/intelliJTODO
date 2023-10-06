package org.iclass.dto;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto {			//javax.validation 으로 서버단에서 유효성 검사.
    private long tno;


    @NotEmpty 		//내용이 없는것 허용 안함.
    @Size(min=3)	//최소 글자수
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future 		//서버에서 값 검증하는 애노테이션 - 오늘 이후의 날짜만 유효함.
    private LocalDate dueDate;

    @NotEmpty private String writer;
    private boolean finished;
}
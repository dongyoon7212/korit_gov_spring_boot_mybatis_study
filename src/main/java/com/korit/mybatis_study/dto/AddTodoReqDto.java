package com.korit.mybatis_study.dto;

import com.korit.mybatis_study.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddTodoReqDto {
    private String title;
    private String content;

    public Todo toEntity() {
        return Todo.builder()
                .title(title)
                .content(content)
                .build();
    }
}

package com.korit.mybatis_study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo {
    private Integer todoId;
    private String title;
    private String content;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;
}

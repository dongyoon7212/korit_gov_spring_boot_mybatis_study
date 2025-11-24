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
public class Board {
    private Integer boardId;
    private String title;
    private String content;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;
}

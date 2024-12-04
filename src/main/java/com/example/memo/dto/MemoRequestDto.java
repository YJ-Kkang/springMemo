package com.example.memo.dto;

import lombok.Getter;

@Getter
public class MemoRequestDto {
    private Long id;
    private String title;
    private String contents;
}
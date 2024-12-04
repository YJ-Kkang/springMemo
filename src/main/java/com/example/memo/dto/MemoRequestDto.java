package com.example.memo.dto;

import com.example.memo.entity.Memo;
import lombok.Getter;

@Getter
public class MemoRequestDto {
    private Long id;
    private String title;
    private String contents;

    //memo 객체가 RequestDto 형태로 바껴서 응답이 되어야 함
    public MemoRequestDto(Memo memo) {
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.contents = memo.getContents();
    }
}
package com.example.memo.dto;

import com.example.memo.entity.Memo;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Getter
public class MemoRequestDto {
    private Long id;
    private String title;
    private String contents;

    //기본 생성자
    public MemoRequestDto() {}

    //memo 객체가 RequestDto 형태로 바껴서 응답이 되어야 함
    public MemoRequestDto(Memo memo) {
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.contents = memo.getContents();
    }
}
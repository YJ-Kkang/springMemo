package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.entity.Memo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController

public class MemoController {
    private final Map<Long, Memo> memoList = new HashMap<>();
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto dto) {
        //식별자가 1씩 증가하도록

        //요청받은 데이터로 Memo 객체 생성

        //Inmemory DB에 Memo 저장(자바의 맵절 구조를 사용하여 그 안에 저장)
    }
}

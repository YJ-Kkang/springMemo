package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController

public class MemoController {
    private final Map<Long, Memo> memoList = new HashMap<>();
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto dto) {
        //식별자가 1씩 증가하도록
        //Collections.max는 이 안에 있는 것 중 최대값 뽑아냄
        //memoList.keySet()은 memoList 안에 있는 key 값들을 다 꺼내보는  것
        // key값을 Long으로 했음 -> 모든 Long값을 꺼내서 그 최대값을 반환해주는 것
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;

        //요청받은 데이터로 Memo 객체 생성
        Memo memo = new Memo(memoId, dto.getTitle(), dto.getContents());

        //Inmemory DB에 Memo 저장(자바의 맵절 구조를 사용하여 그 안에 저장)
        //키값은 memoId, 저장될 객체 형태는 memo
        memoList.put(memoId, memo);

        return new MemoResponseDto();
    }
}

package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/memos") // prefix
public class MemoController {
    private final Map<Long, Memo> memoList = new HashMap<>();

    //메모 생성. 값을 돌려받고 싶을 때 post
    @PostMapping
    //메모값이 응답되는 것(Dto에 저장되어 있음) ( 요청 이름 : 매개변수(값을 넣었을 때 여기에서 해줘라))
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto requestDto) {
        /*
        식별자가 1씩 증가하도록
        Collections.max는 이 안에 있는 것 중 최대값 뽑아냄
        memoList.keySet()은 memoList 안에 있는 key 값들을 다 꺼내보는 것
        key값을 Long으로 했음 -> 모든 Long값을 꺼내서 그 최대값을 반환해주는 것
        isEmpty는 비어있니?라는 뜻
        삼항연산자 == 조건문 ? 맞는 거 : 틀린 거
        */
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;
        //요청받은 데이터로 Memo 객체 생성
        // Memo 클래스를 자료형(Memo)으로 쓸 수 있음
        Memo memo = new Memo(memoId, requestDto.getTitle(), requestDto.getContents());
        /*
        Inmemory DB에 Memo 저장(자바의 맵절 구조를 사용하여 그 안에 저장)
        키값은 memoId, 저장될 객체 형태는 memo
        */
        memoList.put(memoId, memo);
        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.CREATED);
    }

    //memo 전체 조회
    @GetMapping
    public ResponseEntity<List<MemoResponseDto>> findAllMemos() {
        List<MemoResponseDto> responseList = new ArrayList<>();
        for (Memo memo : memoList.values()) {
            MemoResponseDto responseDto = new MemoResponseDto(memo);
            responseList.add(responseDto);
        }
        //Map to List(위 코드와 결과는 똑같음. 추후 적응되면 아래 방법도 사용하기)
//        responseList = memoList.values().stream().map(MemoResponseDto::new).toList();

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    //memo 단건 조회 (Long id 값에 따라 조회하겠다)
    @GetMapping("/{id}")
    //요청 경로변수용 어노테이션
    public ResponseEntity<MemoResponseDto> findMemoById(@PathVariable Long id) {
        //get(i)는 인덱스로 쓴다는 것
        Memo memo = memoList.get(id);
        if (memo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    //메모 수정 기능(dto 쓸 때 requestDto처럼 자세히 쓰기)
    @PutMapping("/{id}")
    public MemoResponseDto updateMemoById(
        @PathVariable Long id,
        @RequestBody MemoRequestDto requestDto
    ) {
        Memo memo = memoList.get(id);
        memo.update(requestDto);
        return new MemoResponseDto(memo);
    }



    //메모 삭제 기능
    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id) {
        memoList.remove(id);
    }
}


@PutMapping("/{id}")
public ResponseEntity<MemoResponseDto> updateMemo(
        @PathVariable Long id,
        @RequestBody MemoRequestDto requestDto
) {

   Memo memo = memoList.get(id);

    // NPE 방지
    if (memo == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 필수값 검증
    if (requestDto.getTitle() == null || requestDto.getContents() == null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // memo 수정
    memo.update(requestDto);

    // 응답
    return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
}
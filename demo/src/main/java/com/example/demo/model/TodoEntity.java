package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class TodoEntity {

    private String id; // 오브젝트 아이디
    private String userId;  // 오브젝트 생성한 유저 아이디
    private String title; // Todo 타이틀
    private boolean done; // true - todo 완료시(checked)
}

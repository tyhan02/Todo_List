package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping("/test")
    public ResponseEntity<?> testTodo(){
        String str = service.testService();
        List<String> list = new ArrayList<>();
        list.add(str);
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response); }


    @PostMapping
    public ResponseEntity<?> createTodo(
            @AuthenticationPrincipal String userId,
            @RequestBody TodoDTO dto){
        try {
            //(1) TodoEntity로 변환
            TodoEntity entity = TodoDTO.todoEntity(dto);
            //(2) id를 null로 초기화, 생성 당시 id가 없어야 하기 때문
            entity.setId(null);
            //(3) 임시 사용자 아이디 설정
            entity.setUserId(userId);
            //(4) 서비스를 이용해 todo 엔티티 생성
            List<TodoEntity> entities = service.create(entity);
            //(5) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDto 리스트로 변환
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
            //(6) 변환된 todo 리스트를 이용해 responsedto 초기화
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
            //(7) ResponseDto를 리턴
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            //(8) 예외는 dto 대신 error에 메세지 넣어 리턴
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);


        }
    }
    @GetMapping
    public ResponseEntity<?> retrieveTodoList(
            @AuthenticationPrincipal String userId){
              //(1) 서비스 메서드의 retrieve() 메서드를 사용해 Todo 리스트 가져온다
        List<TodoEntity> entities = service.retrieve(userId);

        //(2) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDto 리스트로 변환
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        //(3) 변환된 TodoDto 리스트를 이용해  ResponseDto 초기화
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        //(4) responseDTO를 반환
        return ResponseEntity.ok(response);
    }


    @PutMapping
    public  ResponseEntity<?> updateTodo(@AuthenticationPrincipal String userId,
                                         @RequestBody TodoDTO dto){

        TodoEntity entity = TodoDTO.todoEntity(dto);

        entity.setUserId(userId);

        List<TodoEntity> entities = service.update(entity);

        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@AuthenticationPrincipal String userId,
                                        @RequestBody TodoDTO dto){
        try {
            TodoEntity entity = TodoDTO.todoEntity(dto);

            entity.setUserId(userId);

            List<TodoEntity> entities = service.delete(entity);

            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            return ResponseEntity.ok(response);
        } catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }


}

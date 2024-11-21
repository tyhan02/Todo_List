package com.example.demo.controller;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.TestRequestBodyDTO;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test") //리소스


public class TestController {
    @GetMapping("/testRequestBody")
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO
    ) {
        return "Hello World Id" + testRequestBodyDTO.getId() + "message " +
                testRequestBodyDTO.getMessage();
    }
}


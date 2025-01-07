package com.example.demo.controller;

import com.example.demo.dto.TestRequestBodyDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


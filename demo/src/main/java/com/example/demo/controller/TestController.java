package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("test") //리소스


public class TestController {
    @GetMapping("/{id}")
    public String testControllerWithPathVariables(@PathVariable(required = false) int id)
    {
      return "Hello World id" + id;
    }
}


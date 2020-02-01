package com.rossi.springunittest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {
    @PostMapping("/hello")
    public String hello(){
        return "Hello World";
    }

}

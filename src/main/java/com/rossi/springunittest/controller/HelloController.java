package com.rossi.springunittest.controller;

import com.rossi.springunittest.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @PostMapping("/hello")
    public String hello(){
        return helloService.hello();
    }

}

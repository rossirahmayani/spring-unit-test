package com.rossi.springunittest.service;

import com.rossi.springunittest.model.response.BaseResponse;
import com.rossi.springunittest.model.response.DataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloService {

    public String hello(){
        return "Hello world";
    }
}

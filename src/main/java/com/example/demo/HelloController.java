package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    private StringGateway stringGateway;

    public HelloController(StringGateway stringGateway) {
        this.stringGateway = stringGateway;
    }

    @GetMapping
    public String hello(){
        
        logger.info("before SI");
        String result =  stringGateway.process("hi");
        logger.info("after SI:" + result);

        return result;
    }
}

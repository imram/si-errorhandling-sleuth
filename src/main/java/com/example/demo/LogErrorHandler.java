package com.example.demo;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

@MessageEndpoint
public class LogErrorHandler {

    @Transformer
    public String handle(Message message) {
        return "OOPS";
    }
}

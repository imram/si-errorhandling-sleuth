package com.example.demo;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.ErrorMessage;

public class SleuthWorkAroundInterceptor extends ChannelInterceptorAdapter {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        if (!(message instanceof ErrorMessage)) {
            return message;
        }
        MessagingException payload = (MessagingException) message.getPayload();
        Message<?> failedMessage = payload.getFailedMessage();
        failedMessage = MessageBuilder.fromMessage(failedMessage)
                .removeHeader(MessageHeaders.REPLY_CHANNEL)
                .removeHeader(MessageHeaders.ERROR_CHANNEL)
                .build();
        return new ErrorMessage(new MessagingException(failedMessage, payload), message.getHeaders());
    }

}
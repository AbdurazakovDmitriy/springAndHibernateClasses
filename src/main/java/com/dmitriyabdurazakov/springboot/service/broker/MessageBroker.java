package com.dmitriyabdurazakov.springboot.service.broker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageBroker {

    public void send(String message){
        try {
            log.info(message);
        } catch (Exception e){
            log.warn(e.getMessage());
        }
    }
}

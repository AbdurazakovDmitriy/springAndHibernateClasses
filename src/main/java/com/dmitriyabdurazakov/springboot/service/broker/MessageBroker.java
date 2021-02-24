package com.dmitriyabdurazakov.springboot.service.broker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Slf4j
public class MessageBroker {

    @Transactional(value = Transactional.TxType.MANDATORY)
    public void send(String message){
        log.info(message);
    }
}

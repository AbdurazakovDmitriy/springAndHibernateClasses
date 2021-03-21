package com.dmitriyabdurazakov.springboot.api.exceptionHandler;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RequestErrorInfo {
    private String message;
    private Throwable cause;
    private LocalDateTime time;
}

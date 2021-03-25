package com.dmitriyabdurazakov.springboot.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RegistrationRequest {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}

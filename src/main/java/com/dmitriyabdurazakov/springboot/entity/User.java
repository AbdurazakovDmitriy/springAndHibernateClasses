package com.dmitriyabdurazakov.springboot.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{name.notempty}")
    @Size(min = 4, max = 30, message = "{name.size}")
    private String name;

    @Size(max = 50, message = "{surname.size}")
    private String surname;

    @NotEmpty(message = "{email.notempty}")
    @Email(regexp = ".*@.*", message = "{email.pattern}")
    private String email;
}

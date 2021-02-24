package com.dmitriyabdurazakov.springboot.service.collectors;

import com.dmitriyabdurazakov.springboot.data.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserCollector {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<User> getUsersFromPaths(List<String> paths) {
        List<User> users = new ArrayList<>();
        paths.forEach(path -> {
            try {
                users.add(this.objectMapper.readValue(new File(path), User.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return users;
    }
}

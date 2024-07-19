package com.example.bookstore.service;

import com.example.bookstore.model.User;
import com.example.bookstore.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final String USERS_FILE_PATH = "src/main/resources/data/users.json";

    public Optional<User> authenticate(String username, String password) {
        try {
            List<User> users = JsonUtil.readJsonFile(USERS_FILE_PATH, new TypeReference<List<User>>() {});
            return users.stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst();
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}

package com.example.bookstore.controller;

import com.example.bookstore.model.User;
import com.example.bookstore.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {
    private static final String SECRET_KEY = "your_secret_key"; // Replace with a secure key

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User loginUser) {
        Optional<User> user = userService.authenticate(loginUser.getUsername(), loginUser.getPassword());
        if (user.isPresent()) {
            String token = Jwts.builder()
                    .setSubject(user.get().getUsername())
                    .claim("userId", user.get().getId())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }
}

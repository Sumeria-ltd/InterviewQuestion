package com.example.bookstore.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Cart {

    private Long id;


    private List<Book> books = new ArrayList<>();

    // Getters and setters
}

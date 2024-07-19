package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BookService {
    private static final String BOOKS_FILE_PATH = "src/main/resources/data/books.json";

    public List<Book> getBooks() {
        try {
            return JsonUtil.readJsonFile(BOOKS_FILE_PATH, new TypeReference<List<Book>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addBookToCart(Book book) {
        try {
            List<Book> books = JsonUtil.readJsonFile(BOOKS_FILE_PATH, new TypeReference<List<Book>>() {});
            books.add(book);
            JsonUtil.writeJsonFile(BOOKS_FILE_PATH, books);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
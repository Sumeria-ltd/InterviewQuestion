package com.example.bookstore.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> List<T> readJsonFile(String filePath, TypeReference<List<T>> typeReference) throws IOException {
        return objectMapper.readValue(new File(filePath), typeReference);
    }

    public static <T> void writeJsonFile(String filePath, List<T> data) throws IOException {
        objectMapper.writeValue(new File(filePath), data);
    }
}

package com.example.myaispringapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.myaispringapp.service.OllamaService;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private OllamaService ollamaService;

    @PostMapping
    public ResponseEntity<String> chat(@RequestBody String prompt) {
        String response = ollamaService.askLlama3(prompt);
        return ResponseEntity.ok(response);
    }
}

package com.example.myaispringapp.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myaispringapp.service.OllamaService;

@RestController
public class ChatController {
    @Autowired
    private OllamaService ollamaService;

    @PostMapping
    @RequestMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody String prompt) {
        String response = ollamaService.askLlama3(prompt);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @RequestMapping("/meal")
    public ResponseEntity<String> meal() {
        try {
            Document doc = Jsoup.connect("https://www.bistro-bic.de/").get();
            // 2. Text extrahieren – das musst du ggf. anpassen
            Elements elements = doc.select("main#content_main");

            String prompt = "The following menu from Bistro BIC is available:\n\n" +
                    elements.text() + "\n\n" +
                    "Please provide the dishes available today along with their prices, without any additional text, just the dish names and prices."
                    + "\n" +
                    "If there is any mention of holidays or closures in the upper section, please show that information instead of the dishes."
                    + "\n" +
                    "Reply in the format:\n" +
                    "Dish 1: Price 1\n" +
                    "Dish 2: Price 2\n"
                    + "\n" +
                    "If there are no dishes available, please respond with 'No dishes available today.'"
                    + "\n" +
                    "If there is a holiday or closure, please respond with the information."
                    + "\n" +
                    "Please answer in German.";

            String response = ollamaService.askLlama3(prompt);
            return ResponseEntity.ok(response);
        } catch (java.io.IOException e) {
            return ResponseEntity.status(500).body("Failed to fetch meal data: " + e.getMessage());
        }
    }

}

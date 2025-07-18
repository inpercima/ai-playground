package com.example.myaispringapp.service;

import org.springframework.stereotype.Service;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;

@Service
public class OllamaService {
    private static final String OLLAMA_URL = "http://localhost:11434/api/generate";

    public String askLlama3(String prompt) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(OLLAMA_URL);
            String body = String.format("{\"model\":\"llama3\",\"prompt\":\"%s\"}", prompt.replace("\"", "\\\""));
            post.setEntity(new StringEntity(body));
            post.setHeader("Content-Type", "application/json");
            ClassicHttpResponse response = (ClassicHttpResponse) client.execute(post);
            String responseString = EntityUtils.toString(response.getEntity());
            return responseString;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}

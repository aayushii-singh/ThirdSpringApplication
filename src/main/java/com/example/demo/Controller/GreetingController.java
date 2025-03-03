package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
    // curl -X GET "http://localhost:8080/greeting/?name=Ayushi"
    @GetMapping("/")
    public String getGreeting(@RequestParam(name = "name") String name) {
        return "{\"message\": \"Hello, " + name + " from GET method!\"}";
    }

    // curl -X POST http://localhost:8080/greeting/ -H "Content-Type: application/json" -d '{"message": "Hello, Spring!"}'
    @PostMapping("/")
    public String postGreeting(@RequestBody Map<String, String> request) {
        if (request == null) {
            request = new HashMap<>();
        }
        String message = request.getOrDefault("message", "No message provided");
        return "{\"message\": \"Received message: " + message + "\"}";
    }

    // curl -X PUT http://localhost:8080/greeting/ -H "Content-Type: application/json" -d '{"updatedMessage": "Updated greeting!"}'
    @PutMapping("/")
    public String putGreeting(@RequestBody Map<String, String> request) {
        if (request == null) {
            request = new HashMap<>();
        }
        String updatedMessage = request.getOrDefault("updatedMessage", "No updated message provided");
        return "{\"message\": \"Greeting updated to: " + updatedMessage + "\"}";
    }

    // curl -X DELETE "http://localhost:8080/greeting/?id=123"
    @DeleteMapping("/")
    public String deleteGreeting(@RequestParam(name = "id") int id) {
        return "{\"message\": \"Greeting with ID " + id + " has been deleted.\"}";
    }
}
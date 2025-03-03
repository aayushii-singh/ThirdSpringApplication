package com.example.demo.controller;

import com.example.demo.model.Greeting;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    // Example: GET http://localhost:8080/greeting/?name=Ayushi
    @GetMapping("/")
    public Greeting getGreeting(@RequestParam(name = "name") String name) {
        return new Greeting("Hello, " + name + " from GET method!");
    }

    // Example: POST http://localhost:8080/greeting/
    // Header: Content-Type: application/json
    // Body: {"message": "Hello, Spring!"}
    @PostMapping("/")
    public Greeting postGreeting(@RequestBody Greeting request) {
        String message = request.getMessage() != null ? request.getMessage() : "No message provided";
        return new Greeting("Received message: " + message);
    }

    // Example: PUT http://localhost:8080/greeting/
    // Header: Content-Type: application/json
    // Body: {"message": "Updated greeting!"}
    @PutMapping("/")
    public Greeting putGreeting(@RequestBody Greeting request) {
        String updatedMessage = request.getMessage() != null ? request.getMessage() : "No updated message provided";
        return new Greeting("Greeting updated to: " + updatedMessage);
    }

    // Example: DELETE http://localhost:8080/greeting/?id=123
    @DeleteMapping("/")
    public Greeting deleteGreeting(@RequestParam(name = "id") int id) {
        return new Greeting("Greeting with ID " + id + " has been deleted.");
    }
}

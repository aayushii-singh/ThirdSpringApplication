package com.example.demo.Controller;

import com.example.demo.model.Greeting;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    // Example: GET http://localhost:8080/greeting/
    // Example: GET http://localhost:8080/greeting/?firstName=John
    // Example: GET http://localhost:8080/greeting/?lastName=Doe
    // Example: GET http://localhost:8080/greeting/?firstName=John&lastName=Doe
    @GetMapping("/")
    public Greeting getGreeting(
            @RequestParam(name = "firstName", required = false) Optional<String> firstName,
            @RequestParam(name = "lastName", required = false) Optional<String> lastName) {

        String message;

        if (firstName.isPresent() && lastName.isPresent()) {
            message = "Hello, " + firstName.get() + " " + lastName.get() + "!";
        } else if (firstName.isPresent()) {
            message = "Hello, " + firstName.get() + "!";
        } else if (lastName.isPresent()) {
            message = "Hello, " + lastName.get() + "!";
        } else {
            message = "Hello, World!";
        }

        return new Greeting(message);
    }

    // Existing POST method
    @PostMapping("/")
    public Greeting postGreeting(@RequestBody Greeting request) {
        String message = request.getMessage() != null ? request.getMessage() : "No message provided";
        return new Greeting("Received message: " + message);
    }

    // Existing PUT method
    @PutMapping("/")
    public Greeting putGreeting(@RequestBody Greeting request) {
        String updatedMessage = request.getMessage() != null ? request.getMessage() : "No updated message provided";
        return new Greeting("Greeting updated to: " + updatedMessage);
    }

    // Existing DELETE method
    @DeleteMapping("/")
    public Greeting deleteGreeting(@RequestParam(name = "id") int id) {
        return new Greeting("Greeting with ID " + id + " has been deleted.");
    }
}


package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final UserRepository userRepository;

    // Constructor Injection for Repository
    public GreetingController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET all users
    @GetMapping("/")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    // GET user by ID
    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> "Hello, " + value.getName() + "! Your message: " + value.getMessage())
                .orElse("User not found.");
    }

    @PostMapping("/")
    public String postGreeting(@RequestBody User user) {
        userRepository.save(user);
        return "User saved: " + user.getName() + " - Message: " + user.getMessage();
    }

    // PUT (Update user message)
    @PutMapping("/{id}")
    public String putGreeting(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setMessage(updatedUser.getMessage());  // Update message
            userRepository.save(user);
            return "Updated message for " + user.getName() + ": " + user.getMessage();
        }
        return "User not found.";
    }
    /*
    // DELETE (Remove user by ID)
    @DeleteMapping("/{id}")
    public String deleteGreeting(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User with ID " + id + " has been deleted.";
        }
        return "User not found.";
    }

*/
}
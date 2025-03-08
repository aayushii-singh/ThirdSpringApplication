package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {

    private final UserRepository userRepository;

    public GreetingService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getGreeting(String firstName, String lastName) {
        String message;
        if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()) {
            message = "Hello, " + firstName + " " + lastName + "!";
        } else if (firstName != null && !firstName.isEmpty()) {
            message = "Hello, " + firstName + "!";
        } else if (lastName != null && !lastName.isEmpty()) {
            message = "Hello, " + lastName + "!";
        } else {
            message = "Hello, World!";
        }
        return new User(null, message);
    }

    public User createGreeting(User user) {
        return userRepository.save(user);
    }

    public User getGreetingById(Long id) {
        return userRepository.findById(id)
                .orElse(new User("Unknown", "Greeting not found!"));
    }

    public List<User> getAllGreetings() {
        return userRepository.findAll();
    }

    public User updateGreeting(Long id, User user) {
        Optional<User> existing = userRepository.findById(id);
        if (existing.isPresent()) {
            User updated = existing.get();
            updated.setMessage(user.getMessage());
            return userRepository.save(updated);
        }
        return new User("Unknown", "Greeting not found!");
    }

    public String deleteGreeting(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "Greeting deleted successfully!";
        }
        return "Greeting not found!";
    }
}

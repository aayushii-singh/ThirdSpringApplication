/* package com.example.demo.model;

public class User {
    private String name;
    private String message;

    public User() {}

    public User(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
*/
package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")  // Creates 'users' table in H2
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment ID
    private Long id;

    private String name;
    private String message;

    public User() {}

    public User(String name, String message) {
        this.name = name;
        this.message = message;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.example.faceid.model;

import jakarta.persistence.*;

@Entity
@Table(name = "passwords")
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appName;
    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Password() {}

    public void setUser(User user) {
    }

    // getters e setters
}

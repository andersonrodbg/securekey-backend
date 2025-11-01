package com.example.faceid.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;

    public User() {}

    public User(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // getters e setters
}

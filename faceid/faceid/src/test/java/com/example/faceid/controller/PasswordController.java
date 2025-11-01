package com.example.faceid.controller;

import com.example.faceid.model.Password;
import com.example.faceid.model.User;
import com.example.faceid.repository.PasswordRepository;
import com.example.faceid.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passwords")
public class PasswordController {

    private final PasswordRepository passwordRepo;
    private final UserRepository userRepo;

    public PasswordController(PasswordRepository passwordRepo, UserRepository userRepo) {
        this.passwordRepo = passwordRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/{userId}")
    public Password savePassword(@PathVariable Long userId, @RequestBody Password password) {
        User user = userRepo.findById(userId).orElseThrow();
        password.setUser(user);
        return passwordRepo.save(password);
    }

    @GetMapping("/{userId}")
    public List<Password> getPasswords(@PathVariable Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        return passwordRepo.findByUser(user);
    }
}

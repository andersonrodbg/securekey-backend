package com.example.faceid.repository;

import com.example.faceid.model.Password;
import com.example.faceid.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordRepository extends JpaRepository<Password, Long> {
    List<Password> findByUser(User user);
}

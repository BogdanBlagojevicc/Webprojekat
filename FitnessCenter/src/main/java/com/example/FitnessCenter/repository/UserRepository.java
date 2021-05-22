package com.example.FitnessCenter.repository;

import com.example.FitnessCenter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

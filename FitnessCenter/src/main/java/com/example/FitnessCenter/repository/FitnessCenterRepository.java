package com.example.FitnessCenter.repository;

import com.example.FitnessCenter.model.FitnessCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FitnessCenterRepository extends JpaRepository<FitnessCenter, Long> {

    FitnessCenter findOneByEmail(String email);
}

package com.example.FitnessCenter.repository;

import com.example.FitnessCenter.model.FitnessCenter;
import com.example.FitnessCenter.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FitnessCenterRepository extends JpaRepository<FitnessCenter, Long> {

    FitnessCenter findOneByEmail(String email);

}

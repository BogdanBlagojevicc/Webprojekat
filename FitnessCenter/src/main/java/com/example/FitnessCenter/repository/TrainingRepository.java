package com.example.FitnessCenter.repository;

import com.example.FitnessCenter.model.Training;
import com.example.FitnessCenter.model.dto.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findByNameContainingIgnoreCase(String name);

    List<Training> findByType(Type type);

    List<Training> findByDescriptionContainingIgnoreCase(String description);


}

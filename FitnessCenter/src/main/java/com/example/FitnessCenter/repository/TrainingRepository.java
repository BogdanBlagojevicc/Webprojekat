package com.example.FitnessCenter.repository;

import com.example.FitnessCenter.model.Training;
import com.example.FitnessCenter.model.dto.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findAllByName(String name);

    List<Training> findAllByType(Type type);

    List<Training> findAllByDescription(String description);

}

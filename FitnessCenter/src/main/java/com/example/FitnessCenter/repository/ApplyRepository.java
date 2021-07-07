package com.example.FitnessCenter.repository;

import com.example.FitnessCenter.model.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {

    Apply findByTermId(Long id);


}

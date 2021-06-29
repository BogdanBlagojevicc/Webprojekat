package com.example.FitnessCenter.repository;

import com.example.FitnessCenter.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {

    List<Hall> findAllByFitnessCenterId(Long id);
}

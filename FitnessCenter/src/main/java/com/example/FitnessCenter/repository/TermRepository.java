package com.example.FitnessCenter.repository;

import com.example.FitnessCenter.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermRepository extends JpaRepository<Term, Long> {

    List<Term> findAllByPrice(double price);
}

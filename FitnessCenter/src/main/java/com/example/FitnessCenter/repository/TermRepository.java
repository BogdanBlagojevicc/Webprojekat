package com.example.FitnessCenter.repository;

import com.example.FitnessCenter.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TermRepository extends JpaRepository<Term, Long> {

    List<Term> findAllByPrice(Double price);

    List<Term> findAllByStart(Date date);
}

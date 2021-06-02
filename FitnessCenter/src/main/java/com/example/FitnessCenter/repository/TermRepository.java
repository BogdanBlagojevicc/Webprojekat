package com.example.FitnessCenter.repository;

import com.example.FitnessCenter.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {

    List<Term> findAllByPrice(Double price);

    List<Term> findAllByStart(Date date);

    List<Term> findByOrderByPriceAsc();

    List<Term> findByOrderByPriceDesc();

    List<Term> findByOrderByStartAsc();

    List<Term> findByOrderByStartDesc();
}

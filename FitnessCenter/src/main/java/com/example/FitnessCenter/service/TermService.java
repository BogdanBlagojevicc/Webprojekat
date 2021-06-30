package com.example.FitnessCenter.service;

import com.example.FitnessCenter.model.Term;
import com.example.FitnessCenter.model.dto.Type;

import java.util.Date;
import java.util.List;

public interface TermService {

    Term findOne(Long id);

    List<Term> findAll();

    Term create(Term term) throws Exception;

    Term update(Term term) throws Exception;

    void delete(Long id);

    List<Term> findAllPrice(Double price);

    List<Term> findAllDate(Date date);

    List<Term> sortPriceAsc();

    List<Term> sortPriceDesc();

    List<Term> sortDateAsc();

    List<Term> sortDateDesc();

    List<Term> findAllEverything(String name, Type type, String description, Double price, String date);

    List<Term> findAllEverythingWithoutType(String name, String description, Double price, String date);
}

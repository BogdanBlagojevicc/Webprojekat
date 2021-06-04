package com.example.FitnessCenter.service.impl;

import com.example.FitnessCenter.model.Term;
import com.example.FitnessCenter.repository.TermRepository;
import com.example.FitnessCenter.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TermServiceImpl implements TermService {

    private final TermRepository termRepository;

    @Autowired
    public TermServiceImpl(TermRepository termRepository) {
        this.termRepository = termRepository;
    }

    @Override
    public List<Term> findAllPrice(Double price) {
        List<Term> terms = this.termRepository.findByPriceLessThanEqual(price);
        return terms;
    }

    @Override
    public List<Term> findAllDate(Date date) {
        List<Term> terms = this.termRepository.findByStartLessThanEqual(date);
        return terms;
    }

    @Override
    public List<Term> sortPriceAsc() {
        List<Term> terms = this.termRepository.findByOrderByPriceAsc();
        return terms;
    }

    @Override
    public List<Term> sortPriceDesc() {
        List<Term> terms = this.termRepository.findByOrderByPriceDesc();
        return terms;
    }

    @Override
    public List<Term> sortDateAsc(){
        List<Term> terms = this.termRepository.findByOrderByStartAsc();
        return terms;
    }

    @Override
    public List<Term> sortDateDesc(){
        List<Term> terms = this.termRepository.findByOrderByStartDesc();
        return terms;
    }

    @Override
    public List<Term> findAll() {
        List<Term> terms = this.termRepository.findAll();
        return terms;
    }

    @Override
    public Term findOne(Long id) {
        return null;
    }

    @Override
    public Term create(Term term) throws Exception {
        return null;
    }

    @Override
    public Term update(Term term) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}

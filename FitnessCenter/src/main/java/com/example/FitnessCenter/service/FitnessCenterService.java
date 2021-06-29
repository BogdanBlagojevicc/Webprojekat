package com.example.FitnessCenter.service;

import com.example.FitnessCenter.model.FitnessCenter;

import java.util.List;

public interface FitnessCenterService {

    FitnessCenter findOne(Long id);

    FitnessCenter findOneEmail(String email);

    List<FitnessCenter> findAll();

    FitnessCenter create(FitnessCenter fitnessCenter) throws Exception;

    FitnessCenter update(FitnessCenter fitnessCenter) throws Exception;

    FitnessCenter delete(FitnessCenter fitnessCenter) throws Exception;
}

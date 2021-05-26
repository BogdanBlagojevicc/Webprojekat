package com.example.FitnessCenter.service;

import com.example.FitnessCenter.model.FitnessCenter;

import java.util.List;

public interface FitnessCenterService {

    FitnessCenter findOne(Long id);

    List<FitnessCenter> findAll();

    FitnessCenter create(FitnessCenter fitnessCenter) throws Exception;

    FitnessCenter update(FitnessCenter fitnessCenter) throws Exception;

    void delete(Long id);
}

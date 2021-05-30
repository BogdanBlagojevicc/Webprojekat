package com.example.FitnessCenter.service;

import com.example.FitnessCenter.model.Training;

import java.util.List;

public interface TrainingService {

    Training findOne(Long id);

    List<Training> findAll();

    Training create(Training training) throws Exception;

    Training update(Training training) throws Exception;

    void delete(Long id);

    List<Training> findAllName(String name);
}

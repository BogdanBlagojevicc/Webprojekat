package com.example.FitnessCenter.service;

import com.example.FitnessCenter.model.Training;
import com.example.FitnessCenter.model.dto.Type;

import java.util.List;

public interface TrainingService {

    Training findOne(Long id);

    List<Training> findAll();

    Training create(Training training) throws Exception;

    Training update(Training training) throws Exception;

    void delete(Long id);

    List<Training> findAllName(String name);

    List<Training> findAllType(Type type);    //menjam

    List<Training> findAllDescription(String description);
}

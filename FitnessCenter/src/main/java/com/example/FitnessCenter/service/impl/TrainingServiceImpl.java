package com.example.FitnessCenter.service.impl;

import com.example.FitnessCenter.model.Training;
import com.example.FitnessCenter.repository.TrainingRepository;
import com.example.FitnessCenter.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Training findOne(Long id) {
        Training training = this.trainingRepository.getOne(id);
        return training;
    }

    @Override
    public List<Training> findAll() {
        List<Training> trainings = this.trainingRepository.findAll();
        return trainings;
    }

    @Override
    public Training create(Training training) throws Exception {
        if (training.getId() != null) {
            throw new Exception("id must be null!");
        }

        Training newTraining = this.trainingRepository.save(training);
        return newTraining;
    }

    @Override
    public void delete(Long id) {
        this.trainingRepository.deleteById(id);
    }


    @Override
    public List<Training> findAllName(String name) {
        List<Training> trainings = this.trainingRepository.findAllByName(name);
        return trainings;
    }


    @Override
    public List<Training> findAllDescription(String description) {
        List<Training> trainings = this.trainingRepository.findAllByDescription(description);
        return trainings;
    }

    //ostalo
    @Override
    public Training update(Training user) throws Exception {
        return null;
    }
}

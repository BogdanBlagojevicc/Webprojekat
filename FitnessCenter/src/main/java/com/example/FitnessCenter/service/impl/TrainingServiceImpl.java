package com.example.FitnessCenter.service.impl;

import com.example.FitnessCenter.model.Training;
import com.example.FitnessCenter.model.dto.Type;
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
    public Training findByTypeAndTrainerId(Type type, Long id) {
        Training training = this.trainingRepository.findByTypeAndTrainerId(type, id);
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
        List<Training> trainings = this.trainingRepository.findByNameContainingIgnoreCase(name);
        return trainings;
    }

    @Override
    public List<Training> findAllType(Type type) {
        List<Training> trainings = this.trainingRepository.findByType(type);
        return trainings;
    }

    @Override
    public List<Training> findAllDescription(String description) {
        List<Training> trainings = this.trainingRepository.findByDescriptionContainingIgnoreCase(description);
        return trainings;
    }

    @Override
    public Training update(Training training) throws Exception {
        Training trainingToUpdate = this.trainingRepository.getOne(training.getId());
        if (trainingToUpdate == null) {
            throw new Exception("Training doesn't exist!");
        }

        trainingToUpdate.setDescription(training.getDescription());
        trainingToUpdate.setDuration(training.getDuration());
        trainingToUpdate.setType(training.getType());
        trainingToUpdate.setName(training.getName());

        Training savedTraining = this.trainingRepository.save(trainingToUpdate);
        return savedTraining;
    }
}

package com.example.FitnessCenter.service.impl;

import com.example.FitnessCenter.model.FitnessCenter;
import com.example.FitnessCenter.repository.FitnessCenterRepository;
import com.example.FitnessCenter.service.FitnessCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitnessCenterImpl implements FitnessCenterService {

    private final FitnessCenterRepository fitnessCenterRepository;

    @Autowired
    public FitnessCenterImpl(FitnessCenterRepository fitnessCenterRepository) {
        this.fitnessCenterRepository = fitnessCenterRepository;
    }

    //napisao
    @Override
    public FitnessCenter create(FitnessCenter fitnessCenter) throws Exception {
        if (fitnessCenter.getId() != null) {
            throw new Exception("id must be null!");
        }

        FitnessCenter newFitnessCenter = this.fitnessCenterRepository.save(fitnessCenter);
        return newFitnessCenter;
    }

    //napisao
    @Override
    public void delete(Long id) {
        this.fitnessCenterRepository.deleteById(id);
    }

    @Override
    public FitnessCenter findOne(Long id) {
        return null;
    }

    @Override
    public List<FitnessCenter> findAll() {
        return null;
    }

    @Override
    public FitnessCenter update(FitnessCenter fitnessCenter) throws Exception {
        return null;
    }


}

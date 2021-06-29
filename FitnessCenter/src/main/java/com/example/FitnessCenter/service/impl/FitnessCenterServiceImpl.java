package com.example.FitnessCenter.service.impl;

import com.example.FitnessCenter.model.FitnessCenter;
import com.example.FitnessCenter.repository.FitnessCenterRepository;
import com.example.FitnessCenter.service.FitnessCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitnessCenterServiceImpl implements FitnessCenterService {

    private final FitnessCenterRepository fitnessCenterRepository;

    @Autowired
    public FitnessCenterServiceImpl(FitnessCenterRepository fitnessCenterRepository) {
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
    public List<FitnessCenter> findAll() {
        List<FitnessCenter> fitnessCenters = this.fitnessCenterRepository.findAll();
        return fitnessCenters;
    }

    //napisao
    @Override
    public FitnessCenter findOne(Long id) {
        FitnessCenter fitnessCenter = this.fitnessCenterRepository.getOne(id);
        return fitnessCenter;
    }

    @Override
    public FitnessCenter findOneEmail(String email) {
        FitnessCenter fitnessCenter = this.fitnessCenterRepository.findOneByEmail(email);
        return fitnessCenter;
    }

    //napisao
    @Override
    public FitnessCenter update(FitnessCenter fitnessCenter) throws Exception {
        FitnessCenter fitnessCenterToUpdate = this.fitnessCenterRepository.getOne(fitnessCenter.getId());
        if (fitnessCenterToUpdate == null) {
            throw new Exception("Fitness center doesn't exist");
        }

        if (!fitnessCenter.getName().equals("")) {
            fitnessCenterToUpdate.setName(fitnessCenter.getName());
        }
        if (!fitnessCenter.getEmail().equals("")) {
            fitnessCenterToUpdate.setEmail(fitnessCenter.getEmail());
        }
        if (!fitnessCenter.getPhoneNumber().equals("")) {
            fitnessCenterToUpdate.setPhoneNumber(fitnessCenter.getPhoneNumber());
        }
        if (!fitnessCenter.getAddress().equals("")) {
            fitnessCenterToUpdate.setAddress(fitnessCenter.getAddress());
        }

        FitnessCenter savedFitnessCenter = this.fitnessCenterRepository.save(fitnessCenterToUpdate);
        return savedFitnessCenter;
    }

    //napisao
    @Override
    public FitnessCenter delete(FitnessCenter fitnessCenter) throws Exception {
        FitnessCenter fitnessCenterToDelete = this.fitnessCenterRepository.getOne(fitnessCenter.getId());
        if(fitnessCenterToDelete == null){
            throw new Exception("Fitness center doesn't exist");
        }

        fitnessCenterToDelete.setIsDeleted(true);

        FitnessCenter savedFitnessCenter = this.fitnessCenterRepository.save(fitnessCenterToDelete);
        return savedFitnessCenter;
    }


}

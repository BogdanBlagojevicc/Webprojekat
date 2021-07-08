package com.example.FitnessCenter.service.impl;

import com.example.FitnessCenter.model.Hall;
import com.example.FitnessCenter.repository.HallRepository;
import com.example.FitnessCenter.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;

    @Autowired
    public HallServiceImpl(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public Hall create(Hall hall) throws Exception {
        if (hall.getId() != null) {
            throw new Exception("id must be null");
        }

        Hall newHall = this.hallRepository.save(hall);
        return newHall;
    }

    @Override
    public List<Hall> findAll(Long fcId) {
        List<Hall> halls = this.hallRepository.findAllByFitnessCenterId(fcId);
        return halls;
    }

    @Override
    public Hall update(Hall hall) throws Exception {
        Hall hallToUpdate = this.hallRepository.getOne(hall.getId());
        if(hallToUpdate == null){
            throw new Exception("Hall doesn't exist");
        }

        if(hall.getCapacity() != null){
            hallToUpdate.setCapacity(hall.getCapacity());
        }
        if(!hall.getMark().equals("")){
            hallToUpdate.setMark(hall.getMark());
        }

        Hall savedHall = this.hallRepository.save(hallToUpdate);
        return savedHall;
    }

    @Override
    public Hall findOne(Long id){
        Hall hall = this.hallRepository.getOne(id);
        return hall;
    }

    @Override
    public Hall findOneByMark(String mark){
        Hall hall1  = this.hallRepository.findByMark(mark);
        return hall1;
    }

    @Override
    public Hall delete(Hall hall) throws Exception{
        Hall hallToDelete = this.hallRepository.getOne(hall.getId());
        if(hallToDelete == null){
            throw new Exception("Hall doesn't exist");
        }

        hallToDelete.setIsDeleted(true);

        Hall savedHall = this.hallRepository.save(hallToDelete);
        return savedHall;
    }
}

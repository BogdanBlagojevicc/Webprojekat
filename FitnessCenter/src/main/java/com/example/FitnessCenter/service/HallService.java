package com.example.FitnessCenter.service;

import com.example.FitnessCenter.model.Hall;

import java.util.List;

public interface HallService {

    Hall create (Hall hall) throws Exception;

    List<Hall> findAll(Long fcId);

    Hall update(Hall hall) throws Exception;

    Hall findOne(Long id);

    Hall delete(Hall hall) throws Exception;
}

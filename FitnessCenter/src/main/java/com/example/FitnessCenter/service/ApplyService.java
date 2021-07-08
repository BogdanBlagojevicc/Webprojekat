package com.example.FitnessCenter.service;

import com.example.FitnessCenter.model.Apply;
import com.example.FitnessCenter.model.User;

import java.util.List;

public interface ApplyService {

    Apply create (Apply apply) throws Exception;

    List<Apply> findAll();

//    Apply update(Apply apply) throws Exception;

    Apply findOne(Long id);

    Apply findOneByTermId(Long id);

    Apply findOneByTermIdAndSportistsId(Long termId, Long sportistsId);

    Apply delete(Apply apply) throws Exception;

    Apply done(Apply apply) throws Exception;

    Apply grade(Apply apply, User user) throws Exception;

//    Apply grade(Apply apply) throws Exception;

    void save(Apply apply);
}

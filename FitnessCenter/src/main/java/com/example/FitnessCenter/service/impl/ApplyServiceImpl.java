package com.example.FitnessCenter.service.impl;

import com.example.FitnessCenter.model.Apply;
import com.example.FitnessCenter.repository.ApplyRepository;
import com.example.FitnessCenter.service.ApplyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {

    private final ApplyRepository applyRepository;

    public ApplyServiceImpl(ApplyRepository applyRepository) {
        this.applyRepository = applyRepository;
    }


    @Override
    public Apply create(Apply apply) throws Exception {
        if(apply.getId() != null){
            throw new Exception("id must be null");
        }

        Apply newApply = this.applyRepository.save(apply);
        return newApply;
    }

    @Override
    public void save(Apply apply){
        applyRepository.save(apply);
    }

    @Override
    public List<Apply> findAll() {
        List<Apply> applies = this.applyRepository.findAll();
        return applies;
    }

//    @Override
//    public Apply update(Apply apply) throws Exception {
//        return null;
//    }

    @Override
    public Apply findOne(Long id) {
        Apply apply = this.applyRepository.getOne(id);
        return apply;
    }

//    @Override
//    public Apply delete(Apply apply) throws Exception {
//        return null;
//    }
}

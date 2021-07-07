package com.example.FitnessCenter.service.impl;

import com.example.FitnessCenter.model.Apply;
import com.example.FitnessCenter.model.Term;
import com.example.FitnessCenter.repository.ApplyRepository;
import com.example.FitnessCenter.repository.TermRepository;
import com.example.FitnessCenter.service.ApplyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {

    private final ApplyRepository applyRepository;
    private final TermRepository termRepository;

    public ApplyServiceImpl(ApplyRepository applyRepository, TermRepository termRepository) {
        this.applyRepository = applyRepository;
        this.termRepository = termRepository;
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

    @Override
    public Apply findOneByTermId(Long id){
        Apply apply = this.applyRepository.findByTermId(id);
        return apply;
    }

    @Override
    public Apply delete(Apply apply) throws Exception {
        Apply applyToDelete = this.applyRepository.getOne(apply.getId());
        if(applyToDelete == null){
            throw new Exception("Apply doesn't exist");
        }

        applyToDelete.setIsDeleted(true);

        Term term = this.termRepository.getOne(apply.getTerm().getId());
        term.setNumber_of_applications(term.getNumber_of_applications()-1);
        this.termRepository.save(term);

        Apply savedApply = this.applyRepository.save(applyToDelete);
        return savedApply;
    }

    @Override
    public Apply done(Apply apply) throws Exception{
        Apply applyToDone = this.applyRepository.getOne(apply.getId());
        if(applyToDone == null){
            throw new Exception("Apply doesn't exist!");
        }

        applyToDone.setDone(true);

        Term term = this.termRepository.getOne(apply.getTerm().getId());
        term.setNumber_of_applications(term.getNumber_of_applications()-1);
        this.termRepository.save(term);

        Apply savedApply = this.applyRepository.save(applyToDone);
        return savedApply;
    }
}

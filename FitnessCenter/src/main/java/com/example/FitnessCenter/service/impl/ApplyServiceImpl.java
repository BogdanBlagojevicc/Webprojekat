package com.example.FitnessCenter.service.impl;

import com.example.FitnessCenter.model.Apply;
import com.example.FitnessCenter.model.Term;
import com.example.FitnessCenter.model.User;
import com.example.FitnessCenter.repository.ApplyRepository;
import com.example.FitnessCenter.repository.TermRepository;
import com.example.FitnessCenter.repository.UserRepository;
import com.example.FitnessCenter.service.ApplyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {

    private final ApplyRepository applyRepository;
    private final TermRepository termRepository;
    private final UserRepository userRepository;

    public ApplyServiceImpl(ApplyRepository applyRepository, TermRepository termRepository, UserRepository userRepository) {
        this.applyRepository = applyRepository;
        this.termRepository = termRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Apply create(Apply apply) throws Exception {
        if (apply.getId() != null) {
            throw new Exception("id must be null");
        }

        Apply newApply = this.applyRepository.save(apply);
        return newApply;
    }

    @Override
    public void save(Apply apply) {
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
    public Apply findOneByTermId(Long termId) {
        Apply apply = this.applyRepository.findByTermId(termId);
        return apply;
    }

    @Override
    public Apply findOneByTermIdAndSportistsId(Long termId, Long sportistsId){
        Apply apply = this.applyRepository.findByTermIdAndSportistsId(termId, sportistsId);
        return apply;
    }

//    @Override
//    public Apply findOneByTermIdAndSports_Man_id(Long termId, Long userId){
//        Apply apply = this.applyRepository.findByTermIdAndSports_manOrderById(termId, userId);
//        return apply;
//    }


    @Override
    public Apply delete(Apply apply) throws Exception {
        Apply applyToDelete = this.applyRepository.getOne(apply.getId());
        if (applyToDelete == null) {
            throw new Exception("Apply doesn't exist");
        }

        applyToDelete.setIsDeleted(true);

        Term term = this.termRepository.getOne(apply.getTerm().getId());
        term.setNumber_of_applications(term.getNumber_of_applications() - 1);
        this.termRepository.save(term);

        Apply savedApply = this.applyRepository.save(applyToDelete);
        return savedApply;
    }

    @Override
    public Apply done(Apply apply) throws Exception {
        Apply applyToDone = this.applyRepository.getOne(apply.getId());
        if (applyToDone == null) {
            throw new Exception("Apply doesn't exist!");
        }

        applyToDone.setDone(true);

        Term term = this.termRepository.getOne(apply.getTerm().getId());
        term.setNumber_of_applications(term.getNumber_of_applications() - 1);
        this.termRepository.save(term);

        Apply savedApply = this.applyRepository.save(applyToDone);
        return savedApply;
    }

    @Override
    public Apply grade(Apply apply, User user) throws Exception {
        Apply applyToGrade = this.applyRepository.getOne(apply.getId());
        User userToSave = this.userRepository.getOne(user.getId());

        if (applyToGrade == null) {
            throw new Exception("Apply doesn't exist!");
        }

        if (apply.getGrade() != null) {
            applyToGrade.setGrade(apply.getGrade());

            List<Apply> applyList = applyRepository.findAllByTermId(applyToGrade.getTerm().getId());
            double sum = 0;
            long id = 0;
            for (Apply apply1 : applyList) {
                if (apply1.getDone() == true && apply1.getGrade() != null && apply1.getSportists().getId() == userToSave.getId()) {
                    sum = apply1.getTerm().getTrainer().getAverageGrade() + apply1.getGrade();
                    id = apply1.getTerm().getTrainer().getId();
                }
            }

            double average = sum / applyList.size();
            User trainer = this.userRepository.getOne(id);
            trainer.setAverageGrade(average);
            userRepository.save(trainer);
        }

        Apply savedApply = this.applyRepository.save(applyToGrade);
        return savedApply;

    }
/*              RADI
    @Override
    public Apply grade(Apply apply) throws Exception{
        Apply applyToGrade = this.applyRepository.getOne(apply.getId());

        if(applyToGrade == null){
            throw new Exception("Apply doesn't exist!");
        }

        if(apply.getGrade() != null){
            applyToGrade.setGrade(apply.getGrade());
        }

        Apply savedApply = this.applyRepository.save(applyToGrade);
        return savedApply;

    }*/

}

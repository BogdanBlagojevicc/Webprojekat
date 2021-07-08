package com.example.FitnessCenter.controller;

import com.example.FitnessCenter.model.Apply;
import com.example.FitnessCenter.model.FitnessCenter;
import com.example.FitnessCenter.model.User;
import com.example.FitnessCenter.model.dto.ApplyDTO;
import com.example.FitnessCenter.model.dto.FitnessCenterDTO;
import com.example.FitnessCenter.model.dto.Role;
import com.example.FitnessCenter.service.ApplyService;
import com.example.FitnessCenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/applys")
public class ApplyController {

    private final ApplyService applyService;
    private final UserService userService;

    @Autowired
    public ApplyController(ApplyService applyService, UserService userService) {
        this.applyService = applyService;
        this.userService = userService;
    }

    @PutMapping(value = "/delete/{userId}/{termId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteApply(@PathVariable Long userId, @PathVariable Long termId) throws Exception {

        User user = this.userService.findOne(userId);
        if (user == null || user.getRole() != Role.User) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Apply apply = this.applyService.findOneByTermIdAndSportistsId(termId, userId);

        applyService.delete(apply);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping(value = "/done/{userId}/{termId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> doneApply(@PathVariable Long userId, @PathVariable Long termId) throws Exception {

        User user = this.userService.findOne(userId);
        if (user == null || user.getRole() != Role.User) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Apply apply = this.applyService.findOneByTermIdAndSportistsId(termId, userId);

        applyService.done(apply);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/grade/{userId}/{termId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplyDTO> gradeApply(@PathVariable Long userId, @PathVariable Long termId
            , @RequestBody ApplyDTO applyDTO) throws Exception {

        User user = this.userService.findOne(userId);
        if (user == null || user.getRole() != Role.User) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Apply apply1 = this.applyService.findOneByTermIdAndSportistsId(termId, userId);

        Apply apply = new Apply(applyDTO.getGrade(), applyDTO.getDone(), applyDTO.getIsDeleted());

        apply.setId(apply1.getId());

        Apply gradedApply = applyService.grade(apply, user);

//        Apply gradedApply = applyService.grade(apply);

        ApplyDTO gradedApplyDTO = new ApplyDTO(gradedApply.getId(), gradedApply.getGrade(), gradedApply.getDone()
                , gradedApply.getIsDeleted());

        return new ResponseEntity<>(gradedApplyDTO, HttpStatus.OK);

    }
}

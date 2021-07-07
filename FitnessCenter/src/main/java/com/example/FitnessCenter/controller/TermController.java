package com.example.FitnessCenter.controller;

import com.example.FitnessCenter.model.*;
import com.example.FitnessCenter.model.dto.Role;
import com.example.FitnessCenter.model.dto.*;
import com.example.FitnessCenter.service.ApplyService;
import com.example.FitnessCenter.service.TermService;
import com.example.FitnessCenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping(value = "/api/terms")
public class TermController {

    private final TermService termService;
    private final UserService userService;
    private final ApplyService applyService;

    @Autowired
    public TermController(TermService termService, UserService userService, ApplyService applyService) {
        this.termService = termService;
        this.userService = userService;
        this.applyService = applyService;
    }

    @GetMapping(value = "/price/{price}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getTermsPrice(@PathVariable Double price, @PathVariable Long id) {

        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Term> termList = this.termService.findAllPrice(price);

        List<TermDTO> termDTOS = new ArrayList<>();

        Boolean isDeleted = false;

        for (Term term : termList) {
            Hall mark = term.getHall();
            HallDTO markDTO = new HallDTO(mark.getId(), mark.getCapacity(), mark.getMark(), isDeleted);
            User trainer = term.getTrainer();
            UserDTO trainerDTO = new UserDTO(trainer);
            Training training = term.getTraining();
            TrainingDTO typeDTO = new TrainingDTO(training.getId(), training.getName(), training.getDescription()
                    , training.getType().toString(), training.getDuration());
            TermDTO termDTO = new TermDTO(term.getId(), term.getPrice(), term.getStart().toString()
                    , term.getNumber_of_applications(), markDTO, trainerDTO, typeDTO);
            termDTOS.add(termDTO);
        }
        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/date", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getTermsDate(@RequestParam String date, @RequestParam Long id
            , @RequestParam String name, @RequestParam String type, @RequestParam String description
            , @RequestParam Double price) {
        //        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); dobro je za date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);

        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Term> termList = new ArrayList<>();

        termList = this.termService.findAllEverything(name, type, description, price, date);

        List<TermDTO> termDTOS = new ArrayList<>();

        Boolean isDeleted = false;

        for (Term term : termList) {
            Hall mark = term.getHall();
            HallDTO markDTO = new HallDTO(mark.getId(), mark.getCapacity(), mark.getMark(), isDeleted);
            User trainer = term.getTrainer();
            UserDTO trainerDTO = new UserDTO(trainer);
            Training training = term.getTraining();
            TrainingDTO typeDTO = new TrainingDTO(training.getId(), training.getName(), training.getDescription()
                    , training.getType().toString(), training.getDuration());
            TermDTO termDTO = new TermDTO(term.getId(), term.getPrice(), term.getStart().toString()
                    , term.getNumber_of_applications(), markDTO, trainerDTO, typeDTO);
            termDTOS.add(termDTO);
        }
        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/sortPriceAsc/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getTermsSortAsc(@PathVariable Long id) {

        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Term> termList = this.termService.sortPriceAsc();

        List<TermDTO> termDTOS = new ArrayList<>();

        Boolean isDeleted = false;

        for (Term term : termList) {
            Hall mark = term.getHall();
            HallDTO markDTO = new HallDTO(mark.getId(), mark.getCapacity(), mark.getMark(), isDeleted);
            User trainer = term.getTrainer();
            UserDTO trainerDTO = new UserDTO(trainer);
            Training training = term.getTraining();
            TrainingDTO typeDTO = new TrainingDTO(training.getId(), training.getName(), training.getDescription()
                    , training.getType().toString(), training.getDuration());
            TermDTO termDTO = new TermDTO(term.getId(), term.getPrice(), term.getStart().toString()
                    , term.getNumber_of_applications(), markDTO, trainerDTO, typeDTO);
            termDTOS.add(termDTO);
        }

        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/sortPriceDesc/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getTermsSortDesc(@PathVariable Long id) {

        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Term> termList = this.termService.sortPriceDesc();

        List<TermDTO> termDTOS = new ArrayList<>();

        Boolean isDeleted = false;

        for (Term term : termList) {
            Hall mark = term.getHall();
            HallDTO markDTO = new HallDTO(mark.getId(), mark.getCapacity(), mark.getMark(), isDeleted);
            User trainer = term.getTrainer();
            UserDTO trainerDTO = new UserDTO(trainer);
            Training training = term.getTraining();
            TrainingDTO typeDTO = new TrainingDTO(training.getId(), training.getName(), training.getDescription()
                    , training.getType().toString(), training.getDuration());
            TermDTO termDTO = new TermDTO(term.getId(), term.getPrice(), term.getStart().toString()
                    , term.getNumber_of_applications(), markDTO, trainerDTO, typeDTO);
            termDTOS.add(termDTO);
        }

        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/sortDateAsc/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getTermsSortDateAsc(@PathVariable Long id) {

        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Term> termList = this.termService.sortDateAsc();

        List<TermDTO> termDTOS = new ArrayList<>();

        Boolean isDeleted = false;
        for (Term term : termList) {
            Hall mark = term.getHall();
            HallDTO markDTO = new HallDTO(mark.getId(), mark.getCapacity(), mark.getMark(), isDeleted);
            User trainer = term.getTrainer();
            UserDTO trainerDTO = new UserDTO(trainer);
            Training training = term.getTraining();
            TrainingDTO typeDTO = new TrainingDTO(training.getId(), training.getName(), training.getDescription()
                    , training.getType().toString(), training.getDuration());
            TermDTO termDTO = new TermDTO(term.getId(), term.getPrice(), term.getStart().toString()
                    , term.getNumber_of_applications(), markDTO, trainerDTO, typeDTO);
            termDTOS.add(termDTO);
        }

        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/sortDateDesc/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getTermsSortDateDesc(@PathVariable Long id) {

        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Term> termList = this.termService.sortDateDesc();

        List<TermDTO> termDTOS = new ArrayList<>();

        Boolean isDeleted = false;

        for (Term term : termList) {
            Hall mark = term.getHall();
            HallDTO markDTO = new HallDTO(mark.getId(), mark.getCapacity(), mark.getMark(), isDeleted);
            User trainer = term.getTrainer();
            UserDTO trainerDTO = new UserDTO(trainer);
            Training training = term.getTraining();
            TrainingDTO typeDTO = new TrainingDTO(training.getId(), training.getName(), training.getDescription()
                    , training.getType().toString(), training.getDuration());
            TermDTO termDTO = new TermDTO(term.getId(), term.getPrice(), term.getStart().toString()
                    , term.getNumber_of_applications(), markDTO, trainerDTO, typeDTO);
            termDTOS.add(termDTO);
        }

        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getTerms(@PathVariable Long id) {

        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Term> termList = this.termService.findAll();

        List<TermDTO> termDTOS = new ArrayList<>();

        Boolean isDeleted = false;

        for (Term term : termList) {
            Hall mark = term.getHall();
            HallDTO markDTO = new HallDTO(mark.getId(), mark.getCapacity(), mark.getMark(), isDeleted);
            User trainer = term.getTrainer();
            UserDTO trainerDTO = new UserDTO(trainer);
            Training training = term.getTraining();
            TrainingDTO typeDTO = new TrainingDTO(training.getId(), training.getName(), training.getDescription()
                    , training.getType().toString(), training.getDuration());
            TermDTO termDTO = new TermDTO(term.getId(), term.getPrice(), term.getStart().toString()
                    , term.getNumber_of_applications(), markDTO, trainerDTO, typeDTO);
            termDTOS.add(termDTO);
        }
        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{termId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getTerm(@PathVariable Long termId, @PathVariable Long userId) {

        User user = userService.findOne(userId);
        if (user == null || user.getRole() != Role.User) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Term> termList = this.termService.findAll();

        List<TermDTO> termDTOS = new ArrayList<>();

        Boolean isDeleted = false;
        for (Term term : termList) {
            Hall mark = term.getHall();
            HallDTO markDTO = new HallDTO(mark.getId(), mark.getCapacity(), mark.getMark(), isDeleted);
            User trainer = term.getTrainer();
            UserDTO trainerDTO = new UserDTO(trainer);
            Training training = term.getTraining();
            TrainingDTO typeDTO = new TrainingDTO(training.getId(), training.getName(), training.getDescription()
                    , training.getType().toString(), training.getDuration());
            TermDTO termDTO = new TermDTO(term.getId(), term.getPrice(), term.getStart().toString()
                    , term.getNumber_of_applications(), markDTO, trainerDTO, typeDTO);
            if (term.getId() == termId) {
                termDTOS.add(termDTO);
            }
        }
        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    @PutMapping(value = "/{termId}/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> applyForTerm(@PathVariable Long termId, @PathVariable Long userId) throws Exception {

        User user = this.userService.findOne(userId);
        if (user == null || user.getRole() != Role.User) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }

        Term term = this.termService.findOne(termId);
        List<Apply> applyList = this.applyService.findAll();

        if (term == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (term.getNumber_of_applications() == term.getHall().getCapacity()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        for (Apply apply : applyList) {
            User sportsMan = apply.getSports_man();
            Term term1 = apply.getTerm();
            if (sportsMan.getId() == userId && term1.getId() == termId) {
                if (apply.getIsDeleted() == Boolean.FALSE) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                apply.setIsDeleted(false);
                applyService.save(apply);
                term1.setNumber_of_applications(term1.getNumber_of_applications() + 1);
                termService.save(term1);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }

        Apply apply = new Apply();
        applyService.create(apply);
        apply.setSports_man(user);
        apply.setIsDeleted(Boolean.FALSE);
        apply.setDone(Boolean.FALSE);
        applyService.save(apply);
        term.getTerm_apply().add(apply);
        apply.setTerm(term);
        termService.save(term);
        termService.applyForTerm(termService.findOne(termId));


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/allApplicationsForTerms/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getAllApplicationsForTerms(@PathVariable Long userid) {

        User user = userService.findOne(userid);
        if (user == null || user.getRole() != Role.User) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Term> termList = this.termService.findAll();

        List<TermDTO> termDTOS = new ArrayList<>();

        List<Apply> applyList = this.applyService.findAll();

        Boolean isDeleted = false;

        for (Apply apply : applyList) {
            User sportsMan = apply.getSports_man();
            if (sportsMan.getId() == userid) {
                Term term = apply.getTerm();
                Hall mark = term.getHall();
                HallDTO markDTO = new HallDTO(mark.getId(), mark.getCapacity(), mark.getMark(), isDeleted);
                User trainer = term.getTrainer();
                UserDTO trainerDTO = new UserDTO(trainer);
                Training training = term.getTraining();
                TrainingDTO typeDTO = new TrainingDTO(training.getId(), training.getName(), training.getDescription()
                        , training.getType().toString(), training.getDuration());
                TermDTO termDTO = new TermDTO(term.getId(), term.getPrice(), term.getStart().toString()
                        , term.getNumber_of_applications(), markDTO, trainerDTO, typeDTO);
                if (term.getNumber_of_applications() > 0 && apply.getIsDeleted() == Boolean.FALSE) {
                    termDTOS.add(termDTO);
                }
            }
        }
        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/doneTerms/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getAllDoneTerms(@PathVariable Long userId) {

        User user = userService.findOne(userId);
        if (user == null || user.getRole() != Role.User) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Term> termList = this.termService.findAll();

        List<TermDTO> termDTOS = new ArrayList<>();

        List<Apply> applyList = this.applyService.findAll();

        Boolean isDeleted = false;

        for (Apply apply : applyList) {
            User sportsMan = apply.getSports_man();
            if (sportsMan.getId() == userId) {
                Term term = apply.getTerm();
                Hall mark = term.getHall();
                HallDTO markDTO = new HallDTO(mark.getId(), mark.getCapacity(), mark.getMark(), isDeleted);
                User trainer = term.getTrainer();
                UserDTO trainerDTO = new UserDTO(trainer);
                Training training = term.getTraining();
                TrainingDTO typeDTO = new TrainingDTO(training.getId(), training.getName(), training.getDescription()
                        , training.getType().toString(), training.getDuration());
                TermDTO termDTO = new TermDTO(term.getId(), term.getPrice(), term.getStart().toString()
                        , term.getNumber_of_applications(), markDTO, trainerDTO, typeDTO);
                if (apply.getDone() == true) {
                    termDTOS.add(termDTO);
                }
            }
        }
        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/doneNotGradeTerms/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getAllDoneNotGradeTerms(@PathVariable Long userId) {

        User user = userService.findOne(userId);
        if (user == null || user.getRole() != Role.User) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Term> termList = this.termService.findAll();

        List<TermDTO> termDTOS = new ArrayList<>();

        List<Apply> applyList = this.applyService.findAll();

        Boolean isDeleted = false;

        for (Apply apply : applyList) {
            User sportsMan = apply.getSports_man();
            if (sportsMan.getId() == userId) {
                Term term = apply.getTerm();
                Hall mark = term.getHall();
                HallDTO markDTO = new HallDTO(mark.getId(), mark.getCapacity(), mark.getMark(), isDeleted);
                User trainer = term.getTrainer();
                UserDTO trainerDTO = new UserDTO(trainer);
                Training training = term.getTraining();
                TrainingDTO typeDTO = new TrainingDTO(training.getId(), training.getName(), training.getDescription()
                        , training.getType().toString(), training.getDuration());
                TermDTO termDTO = new TermDTO(term.getId(), term.getPrice(), term.getStart().toString()
                        , term.getNumber_of_applications(), markDTO, trainerDTO, typeDTO);
                if (apply.getDone() == true && apply.getGrade() == null) {
                    termDTOS.add(termDTO);
                }
            }
        }
        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

}
package com.example.FitnessCenter.controller;

import com.example.FitnessCenter.model.Hall;
import com.example.FitnessCenter.model.Term;
import com.example.FitnessCenter.model.Training;
import com.example.FitnessCenter.model.User;
import com.example.FitnessCenter.model.dto.*;
import com.example.FitnessCenter.service.TermService;
import com.example.FitnessCenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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

    @Autowired
    public TermController(TermService termService, UserService userService) {
        this.termService = termService;
        this.userService = userService;
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

        if(type.equals("") || type == null){
            termList = this.termService.findAllEverythingWithoutType(name, description, price, date);
        }else{
            Type type2 = Type.valueOf(type);
            termList = this.termService.findAllEverything(name, type2, description, price, date);
        }

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

}

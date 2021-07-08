package com.example.FitnessCenter.controller;

import com.example.FitnessCenter.model.Hall;
import com.example.FitnessCenter.model.Term;
import com.example.FitnessCenter.model.Training;
import com.example.FitnessCenter.model.User;
import com.example.FitnessCenter.model.dto.*;
import com.example.FitnessCenter.service.TrainingService;
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

@CrossOrigin(origins = "http://localhost:63342")    //ako ocu svi da mogu da gadjaju stavim *
@RestController
@RequestMapping(value = "/api/trainings")
public class TrainingController {

    private final TrainingService trainingService;
    private final UserService userService;

    @Autowired
    public TrainingController(TrainingService trainingService, UserService userService) {
        this.trainingService = trainingService;
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrainingDTO>> getTraining() {

        List<Training> trainingList = this.trainingService.findAll();

        List<TrainingDTO> trainingDTOS = new ArrayList<>();

        for (Training training : trainingList) {
            User trainer = training.getTrainer();
            UserDTO trainerDTO = new UserDTO(trainer);
            TrainingDTO trainingDTO = new TrainingDTO(training.getId(), training.getName(), training.getDescription()
                    , training.getType().toString(), training.getDuration(), trainerDTO);

            trainingDTOS.add(trainingDTO);
        }

        return new ResponseEntity<>(trainingDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{name}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrainingDTO>> getTrainingName(@PathVariable String name, @PathVariable Long id) {

        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        List<Training> trainingList = this.trainingService.findAllName(name);

        List<TrainingDTO> trainingDTOS = new ArrayList<>();

        for (Training training : trainingList) {
            User trainer = training.getTrainer();
            UserDTO trainerDTO = new UserDTO(trainer);
            TrainingDTO trainingDTO = new TrainingDTO(training.getId(), training.getName(), training.getDescription()
                    , training.getType().toString(), training.getDuration(), trainerDTO);
            trainingDTOS.add(trainingDTO);
        }

        return new ResponseEntity<>(trainingDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/description/{description}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrainingDTO>> getTrainingDescription(@PathVariable String description, @PathVariable Long id) {

        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Training> trainingList = this.trainingService.findAllDescription(description);

        List<TrainingDTO> trainingDTOS = new ArrayList<>();

        for (Training training : trainingList) {
            User trainer = training.getTrainer();
            UserDTO trainerDTO = new UserDTO(trainer);
            TrainingDTO trainingDTO = new TrainingDTO(training.getId(), training.getName(), training.getDescription()
                    , training.getType().toString(), training.getDuration(), trainerDTO);
            trainingDTOS.add(trainingDTO);
        }
        return new ResponseEntity<>(trainingDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/type/{type}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrainingDTO>> getTrainingType(@PathVariable String type, @PathVariable Long id) {

        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Type type2 = Type.valueOf(type);
        List<Training> trainingList = this.trainingService.findAllType(type2);

        List<TrainingDTO> trainingDTOS = new ArrayList<>();

        for (Training training : trainingList) {
            User trainer = training.getTrainer();
            UserDTO trainerDTO = new UserDTO(trainer);
            TrainingDTO trainingDTO = new TrainingDTO(training.getId(), training.getName(), training.getDescription()
                    , training.getType().toString(), training.getDuration(), trainerDTO);

            trainingDTOS.add(trainingDTO);
        }

        return new ResponseEntity<>(trainingDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/trainer/createTraining/{trainerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrainingDTO> createTrainerTraining(@RequestBody TrainingDTO trainingDTO, @PathVariable Long trainerId) throws Exception {

        User trainer = userService.findOne(trainerId);
        if (trainer == null || trainer.getRole() != Role.Trainer) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Type type1 = Type.valueOf(trainingDTO.getType());

        Training training = new Training(trainingDTO.getName(), trainingDTO.getDescription(), type1
                , trainingDTO.getDuration(), trainer);

        Training newTraining = trainingService.create(training);

        TrainingDTO newTrainingDTO = new TrainingDTO(newTraining.getId(), newTraining.getName(), newTraining.getDescription(),
                newTraining.getType().toString(), newTraining.getDuration());

        return new ResponseEntity<>(newTrainingDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/trainer/updateTraining/{trainerId}/{trainingId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrainingDTO> updateTrainingTrainer(@PathVariable Long trainerId,
                    @RequestBody TrainingDTO trainingDTO, @PathVariable Long trainingId) throws Exception {

        User trainer = this.userService.findOne(trainerId);
        if (trainer == null || trainer.getRole() != Role.Trainer) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Type type1 = Type.valueOf(trainingDTO.getType());

        if (type1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Training training = new Training(trainingDTO.getName(), trainingDTO.getDescription()
                , type1, trainingDTO.getDuration());

        training.setId(trainingId);

        Training updatedTraining = trainingService.update(training);

        TrainingDTO updatedTrainingDTO = new TrainingDTO(updatedTraining.getId(), updatedTraining.getName(),
                updatedTraining.getDescription(), updatedTraining.getType().toString(), updatedTraining.getDuration());

        return new ResponseEntity<>(updatedTrainingDTO, HttpStatus.OK);

    }

}

package com.example.FitnessCenter.controller;

import com.example.FitnessCenter.model.Training;
import com.example.FitnessCenter.model.User;
import com.example.FitnessCenter.model.dto.Role;
import com.example.FitnessCenter.model.dto.TrainingDTO;
import com.example.FitnessCenter.model.dto.Type;
import com.example.FitnessCenter.model.dto.UserDTO;
import com.example.FitnessCenter.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")    //ako ocu svi da mogu da gadjaju stavim *
@RestController
@RequestMapping(value = "/api/trainings")
public class TrainingController {

    private final TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
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

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrainingDTO>> getTrainingName(@PathVariable String name) {

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

    @GetMapping(value = "/description/{description}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrainingDTO>> getTrainingDescription(@PathVariable String description) {

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

    @GetMapping(value = "/type/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrainingDTO>> getTrainingType(@PathVariable String type) {

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

}

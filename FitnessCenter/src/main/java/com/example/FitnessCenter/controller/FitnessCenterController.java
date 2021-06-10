package com.example.FitnessCenter.controller;


import com.example.FitnessCenter.model.FitnessCenter;
import com.example.FitnessCenter.model.User;
import com.example.FitnessCenter.model.dto.FitnessCenterDTO;
import com.example.FitnessCenter.model.dto.Role;
import com.example.FitnessCenter.service.FitnessCenterService;
import com.example.FitnessCenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping(value = "/api/fitnessCenters")
public class FitnessCenterController {

    private final FitnessCenterService fitnessCenterService;
    private final UserService userService;

    @Autowired
    public FitnessCenterController(FitnessCenterService fitnessCenterService, UserService userService) {
        this.fitnessCenterService = fitnessCenterService;
        this.userService = userService;
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FitnessCenterDTO> createFitnessCenter(@RequestBody FitnessCenterDTO fitnessCenterDTO, @PathVariable Long id) throws Exception {

        User user = userService.findOne(id);
        if (user == null || user.getRole() != Role.Admin) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        FitnessCenter fitnessCenter = new FitnessCenter(fitnessCenterDTO.getName(), fitnessCenterDTO.getAddress()
                , fitnessCenterDTO.getPhoneNumber(), fitnessCenterDTO.getEmail());

        FitnessCenter newFitnessCenter = fitnessCenterService.create(fitnessCenter);

        FitnessCenterDTO newFitnessCenterDTO = new FitnessCenterDTO(newFitnessCenter.getId(), newFitnessCenter.getName()
                , newFitnessCenter.getAddress(), newFitnessCenter.getPhoneNumber(), newFitnessCenter.getEmail());

        return new ResponseEntity<>(newFitnessCenterDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteFitnessCenter(@PathVariable Long id) {
        this.fitnessCenterService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FitnessCenterDTO>> getFitnessCenters() {


        List<FitnessCenter> fitnessCenterList = this.fitnessCenterService.findAll();

        List<FitnessCenterDTO> fitnessCenterDTOS = new ArrayList<>();

        for (FitnessCenter fitnessCenter : fitnessCenterList) {
            FitnessCenterDTO fitnessCenterDTO = new FitnessCenterDTO(fitnessCenter.getId(), fitnessCenter.getName()
                    , fitnessCenter.getAddress(), fitnessCenter.getPhoneNumber(), fitnessCenter.getEmail());
            fitnessCenterDTOS.add(fitnessCenterDTO);
        }

        return new ResponseEntity<>(fitnessCenterDTOS, HttpStatus.OK);
    }
}

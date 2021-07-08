package com.example.FitnessCenter.controller;


import com.example.FitnessCenter.model.FitnessCenter;
import com.example.FitnessCenter.model.Hall;
import com.example.FitnessCenter.model.User;
import com.example.FitnessCenter.model.dto.FitnessCenterDTO;
import com.example.FitnessCenter.model.dto.Role;
import com.example.FitnessCenter.service.FitnessCenterService;
import com.example.FitnessCenter.service.HallService;
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
    private final HallService hallService;

    @Autowired
    public FitnessCenterController(FitnessCenterService fitnessCenterService, UserService userService, HallService hallService) {
        this.fitnessCenterService = fitnessCenterService;
        this.userService = userService;
        this.hallService = hallService;
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FitnessCenterDTO> createFitnessCenter(@RequestBody FitnessCenterDTO fitnessCenterDTO, @PathVariable Long id) throws Exception {

        User user = userService.findOne(id);
        if (user == null || user.getRole() != Role.Admin) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Boolean isDeleted = false;
        FitnessCenter fitnessCenter = new FitnessCenter(fitnessCenterDTO.getName(), fitnessCenterDTO.getAddress()
                , fitnessCenterDTO.getPhoneNumber(), fitnessCenterDTO.getEmail(), isDeleted);

        FitnessCenter newFitnessCenter = fitnessCenterService.create(fitnessCenter);


        FitnessCenterDTO newFitnessCenterDTO = new FitnessCenterDTO(newFitnessCenter.getId(), newFitnessCenter.getName()
                , newFitnessCenter.getAddress(), newFitnessCenter.getPhoneNumber(), newFitnessCenter.getEmail(), isDeleted);

        return new ResponseEntity<>(newFitnessCenterDTO, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FitnessCenterDTO>> getFitnessCenters() {


        List<FitnessCenter> fitnessCenterList = this.fitnessCenterService.findAll();

        List<FitnessCenterDTO> fitnessCenterDTOS = new ArrayList<>();

        Boolean isDeleted = false;
        for (FitnessCenter fitnessCenter : fitnessCenterList) {
            FitnessCenterDTO fitnessCenterDTO = new FitnessCenterDTO(fitnessCenter.getId(), fitnessCenter.getName()
                    , fitnessCenter.getAddress(), fitnessCenter.getPhoneNumber(), fitnessCenter.getEmail(), isDeleted);
            if (fitnessCenter.getIsDeleted() == false) {
                fitnessCenterDTOS.add(fitnessCenterDTO);
            }
        }

        return new ResponseEntity<>(fitnessCenterDTOS, HttpStatus.OK);
    }

    @PutMapping(value = "/{adminId}/{fcId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FitnessCenterDTO> updateFitnessCenter(@PathVariable Long adminId, @PathVariable Long fcId
            , @RequestBody FitnessCenterDTO fitnessCenterDTO) throws Exception {

        User admin = this.userService.findOne(adminId);
        if (admin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (admin.getRole() != Role.Admin) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        Boolean isDeleted = false;
        FitnessCenter fitnessCenter = new FitnessCenter(fitnessCenterDTO.getName(), fitnessCenterDTO.getAddress()
                , fitnessCenterDTO.getPhoneNumber(), fitnessCenterDTO.getEmail(), isDeleted);

        fitnessCenter.setId(fcId);

        FitnessCenter updatedFitnessCenter = fitnessCenterService.update(fitnessCenter);

        FitnessCenterDTO updatedFitnessCenterDTO = new FitnessCenterDTO(updatedFitnessCenter.getId(),
                updatedFitnessCenter.getName(), updatedFitnessCenter.getAddress(), updatedFitnessCenter.getPhoneNumber(),
                updatedFitnessCenter.getEmail(), isDeleted);

        return new ResponseEntity<>(updatedFitnessCenterDTO, HttpStatus.OK);

    }

    @PutMapping(value = "/delete/{adminId}/{fcId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteFitnessCenter(@PathVariable Long adminId, @PathVariable Long fcId) throws Exception {

        User admin = this.userService.findOne(adminId);
        if (admin == null || admin.getRole() != Role.Admin) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        fitnessCenterService.delete(fitnessCenterService.findOne(fcId));

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "/{fcId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FitnessCenterDTO> getFitnessCenter(@PathVariable Long fcId) {


        FitnessCenter fitnessCenter = this.fitnessCenterService.findOne(fcId);


        Boolean isDeleted = false;

        FitnessCenterDTO fitnessCenterDTO = new FitnessCenterDTO(fitnessCenter.getId(), fitnessCenter.getName()
                , fitnessCenter.getAddress(), fitnessCenter.getPhoneNumber(), fitnessCenter.getEmail(), isDeleted);

        return new ResponseEntity<>(fitnessCenterDTO, HttpStatus.OK);
    }
}

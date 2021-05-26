package com.example.FitnessCenter.controller;


import com.example.FitnessCenter.model.FitnessCenter;
import com.example.FitnessCenter.model.dto.FitnessCenterDTO;
import com.example.FitnessCenter.service.FitnessCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/fitnessCenters")
public class FitnessCenterController {

    private final FitnessCenterService fitnessCenterService;

    @Autowired
    public FitnessCenterController(FitnessCenterService fitnessCenterService) {
        this.fitnessCenterService = fitnessCenterService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FitnessCenterDTO> createFitnessCenter(@RequestBody FitnessCenterDTO fitnessCenterDTO) throws Exception {
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
}

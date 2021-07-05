package com.example.FitnessCenter.controller;

import com.example.FitnessCenter.model.FitnessCenter;
import com.example.FitnessCenter.model.Hall;
import com.example.FitnessCenter.model.User;
import com.example.FitnessCenter.model.dto.HallDTO;
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

@CrossOrigin
@RestController
@RequestMapping(value = "/api/halls")
public class HallController {

    private final HallService hallService;
    private final UserService userService;
    private final FitnessCenterService fitnessCenterService;

    @Autowired
    public HallController(HallService hallService, UserService userService, FitnessCenterService fitnessCenterService) {
        this.hallService = hallService;
        this.userService = userService;
        this.fitnessCenterService = fitnessCenterService;
    }

    @PostMapping(value = "/{adminId}/{fcId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HallDTO> createHall(@PathVariable Long adminId, @PathVariable Long fcId
            , @RequestBody HallDTO hallDTO) throws Exception {

        User user = userService.findOne(adminId);
        if (user == null || user.getRole() != Role.Admin) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Boolean isDeleted = false;
        Hall hall = new Hall(hallDTO.getCapacity(), hallDTO.getMark(), isDeleted);

        FitnessCenter fitnessCenter = fitnessCenterService.findOne(fcId);
        hall.setFitnessCenter(fitnessCenter);
        Hall newHall = hallService.create(hall);
        fitnessCenter.getHalls().add(newHall);
        fitnessCenterService.save(fitnessCenter);

        HallDTO newHallDTO = new HallDTO(newHall.getId(), newHall.getCapacity(), newHall.getMark(), isDeleted);

        return new ResponseEntity<>(newHallDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{fcId}/{adminId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HallDTO>> getHalls(@PathVariable Long fcId, @PathVariable Long adminId) {

        User admin = this.userService.findOne(adminId);
        if (admin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (admin.getRole() != Role.Admin) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Hall> hallList = this.hallService.findAll(fcId);

        List<HallDTO> hallDTOS = new ArrayList<>();

        Boolean isDeleted = false;
        for (Hall hall : hallList) {
            HallDTO hallDTO = new HallDTO(hall.getId(), hall.getCapacity(), hall.getMark(), isDeleted);
            if (hall.getIsDeleted() == false) {
                hallDTOS.add(hallDTO);
            }


        }

        return new ResponseEntity<>(hallDTOS, HttpStatus.OK);
    }

    @PutMapping(value = "/{adminId}/{hallId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HallDTO> updateHall(@PathVariable Long adminId, @PathVariable Long hallId
            , @RequestBody HallDTO hallDTO) throws Exception {

        User admin = this.userService.findOne(adminId);
        if (admin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (admin.getRole() != Role.Admin) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Boolean isDeleted = false;
        Hall hall = new Hall(hallDTO.getCapacity(), hallDTO.getMark(), isDeleted);

        hall.setId(hallId);

        Hall updatedHall = hallService.update(hall);

        HallDTO updatedHallDTO = new HallDTO(updatedHall.getId(), updatedHall.getCapacity(), updatedHall.getMark(),
                isDeleted);

        return new ResponseEntity<>(updatedHallDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/delete/{adminId}/{hallId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteHall(@PathVariable Long adminId, @PathVariable Long hallId) throws Exception {

        User admin = this.userService.findOne(adminId);
        if (admin == null || admin.getRole() != Role.Admin) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        hallService.delete(hallService.findOne(hallId));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{hallId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HallDTO> getHall(@PathVariable Long hallId) {

        Hall hall = this.hallService.findOne(hallId);

        Boolean isDeleted = false;

        HallDTO hallDTO = new HallDTO(hall.getId(), hall.getCapacity(), hall.getMark(), isDeleted);


        return new ResponseEntity<>(hallDTO, HttpStatus.OK);
    }
}

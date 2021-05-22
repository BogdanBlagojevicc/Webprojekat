package com.example.FitnessCenter.controller;

import com.example.FitnessCenter.model.User;
import com.example.FitnessCenter.model.dto.Role;
import com.example.FitnessCenter.model.dto.UserDTO;
import com.example.FitnessCenter.service.UserService;
import lombok.val;
import lombok.var;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws Exception{
//        Date date = new Date(userDTO.getBirth());

        Role role = Role.valueOf(userDTO.getRole());
        Boolean isActive = true;
        if(role.equals(Role.Trainer)) {
            isActive = false;
        }

        User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getFirstName(), userDTO.getLastName()
                , userDTO.getPhoneNumber(), userDTO.getEmail(), new Date(), role, isActive, userDTO.getAverageGrade());

        User newUser = userService.create(user);

        UserDTO newUserDTO = new UserDTO(newUser.getId(), newUser.getUsername(), newUser.getPassword(), newUser.getFirstName()
                , newUser.getLastName(), newUser.getPhoneNumber(), newUser.getEmail(), newUser.getBirth().toString(), newUser.getRole().toString()
                , newUser.getActive(), newUser.getAverageGrade());

        return new ResponseEntity<>(newUserDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        this.userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

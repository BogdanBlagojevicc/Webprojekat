package com.example.FitnessCenter.controller;

import com.example.FitnessCenter.model.User;
import com.example.FitnessCenter.model.dto.Role;
import com.example.FitnessCenter.model.dto.UserDTO;
import com.example.FitnessCenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        String dateInString = userDTO.getBirth();
        Date dateBirth = formatter.parse(dateInString);

        Role role = Role.valueOf(userDTO.getRole());
        Boolean isActive = true;
        if (role.equals(Role.Trainer)) {
            isActive = false;
        }

        User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getFirstName(), userDTO.getLastName()
                , userDTO.getPhoneNumber(), userDTO.getEmail(), dateBirth, role, isActive, userDTO.getAverageGrade());

        User newUser = userService.create(user);

        UserDTO newUserDTO = new UserDTO(newUser.getId(), newUser.getUsername(), newUser.getPassword(), newUser.getFirstName()
                , newUser.getLastName(), newUser.getPhoneNumber(), newUser.getEmail(), newUser.getBirth().toString(), newUser.getRole().toString()
                , newUser.getActive(), newUser.getAverageGrade());

        return new ResponseEntity<>(newUserDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        this.userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

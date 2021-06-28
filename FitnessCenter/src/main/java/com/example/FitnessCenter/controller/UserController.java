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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "http://localhost:63342")
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

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

    @GetMapping(value = "/trainersApplyForRegistration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getApplyForRegistration() {

        List<User> userList = this.userService.findAll();

        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : userList) {
            UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName()
                    , user.getLastName(), user.getPhoneNumber(), user.getEmail(), user.getBirth().toString()
                    , user.getRole().toString(), user.getActive(), user.getAverageGrade());
            if (user.getRole() == Role.Trainer && user.getActive() == false) {
                userDTOS.add(userDTO);
            }
        }

        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{username}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> getUser(@PathVariable String username, @PathVariable String password) {

        User user = this.userService.findOneUsernameAndPassword(username, password);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (user.getActive().equals(false)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirth(user.getBirth().toString());
        userDTO.setRole(user.getRole().toString());
        userDTO.setActive(user.getActive());
        userDTO.setAverageGrade(user.getAverageGrade());

        return new ResponseEntity<>(userDTO.getId(), HttpStatus.OK);

    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> getUser(@PathVariable Long id) {

        User user = this.userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!user.getRole().equals(Role.Admin)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirth(user.getBirth().toString());
        userDTO.setRole(user.getRole().toString());
        userDTO.setActive(user.getActive());
        userDTO.setAverageGrade(user.getAverageGrade());

        return new ResponseEntity<>(userDTO.getId(), HttpStatus.OK);

    }

    @GetMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> updateUser(@PathVariable Long id) {

        User user = this.userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!user.getRole().equals(Role.Admin)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirth(user.getBirth().toString());
        userDTO.setRole(user.getRole().toString());
        userDTO.setActive(user.getActive());
        userDTO.setAverageGrade(user.getAverageGrade());

        return new ResponseEntity<>(userDTO.getId(), HttpStatus.OK);

    }


    @PutMapping(value = "/{trainerId}/{adminId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@PathVariable Long trainerId, @PathVariable Long adminId) throws Exception {

        User admin = this.userService.findOne(adminId);
        if (admin.getRole() != Role.Admin) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }

        userService.update(userService.findOne(trainerId));

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        this.userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

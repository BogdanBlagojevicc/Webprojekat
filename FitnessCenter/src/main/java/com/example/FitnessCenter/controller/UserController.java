package com.example.FitnessCenter.controller;

import com.example.FitnessCenter.model.FitnessCenter;
import com.example.FitnessCenter.model.User;
import com.example.FitnessCenter.model.dto.Role;
import com.example.FitnessCenter.model.dto.UserDTO;
import com.example.FitnessCenter.service.FitnessCenterService;
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
    private final FitnessCenterService fitnessCenterService;

    @Autowired
    public UserController(UserService userService, FitnessCenterService fitnessCenterService) {
        this.userService = userService;
        this.fitnessCenterService = fitnessCenterService;
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

        Boolean isDeleted = false;
        User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getFirstName(), userDTO.getLastName()
                , userDTO.getPhoneNumber(), userDTO.getEmail(), dateBirth, role, isActive, userDTO.getAverageGrade(), isDeleted);

        User newUser = userService.create(user);

        UserDTO newUserDTO = new UserDTO(newUser.getId(), newUser.getUsername(), newUser.getPassword(), newUser.getFirstName()
                , newUser.getLastName(), newUser.getPhoneNumber(), newUser.getEmail(), newUser.getBirth().toString(), newUser.getRole().toString()
                , newUser.getActive(), newUser.getAverageGrade(), isDeleted);

        return new ResponseEntity<>(newUserDTO, HttpStatus.CREATED);
    }

    @PostMapping(value = "/{adminId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createTrainer(@PathVariable Long adminId, @RequestBody UserDTO userDTO) throws Exception {

        User admin = this.userService.findOne(adminId);
        if (admin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!admin.getRole().equals(Role.Admin)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        String dateInString = userDTO.getBirth();
        Date dateBirth = formatter.parse(dateInString);

        Role role = Role.valueOf(userDTO.getRole());
        Boolean isActive = true;
        if (role.equals(Role.Trainer)) {
            isActive = true;
        }

        Boolean isDeleted = false;
        User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getFirstName(), userDTO.getLastName()
                , userDTO.getPhoneNumber(), userDTO.getEmail(), dateBirth, role, isActive, userDTO.getAverageGrade(), isDeleted);

        long fcId = 1;
        FitnessCenter fitnessCenter = fitnessCenterService.findOne(fcId);
        user.setFitnessCenter(fitnessCenter);
        User newUser = userService.create(user);
        fitnessCenter.getUsers().add(newUser);
        fitnessCenterService.save(fitnessCenter);

        UserDTO newUserDTO = new UserDTO(newUser.getId(), newUser.getUsername(), newUser.getPassword(), newUser.getFirstName()
                , newUser.getLastName(), newUser.getPhoneNumber(), newUser.getEmail(), newUser.getBirth().toString(), newUser.getRole().toString()
                , newUser.getActive(), newUser.getAverageGrade(), isDeleted);

        return new ResponseEntity<>(newUserDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/trainersApplyForRegistration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getApplyForRegistration() {

        List<User> userList = this.userService.findAll();

        List<UserDTO> userDTOS = new ArrayList<>();

        Boolean isDeleted = false;
        for (User user : userList) {
            UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName()
                    , user.getLastName(), user.getPhoneNumber(), user.getEmail(), user.getBirth().toString()
                    , user.getRole().toString(), user.getActive(), user.getAverageGrade(), isDeleted);
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

    @GetMapping(value = "/isUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> isUser(@PathVariable Long id) {

        User user = this.userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!user.getRole().equals(Role.User)) {
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

    @GetMapping(value = "/isTrainer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> isTrainer(@PathVariable Long id) {

        User trainer = this.userService.findOne(id);
        if (trainer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!trainer.getRole().equals(Role.Trainer)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(trainer.getId());
        userDTO.setUsername(trainer.getUsername());
        userDTO.setPassword(trainer.getPassword());
        userDTO.setFirstName(trainer.getFirstName());
        userDTO.setLastName(trainer.getLastName());
        userDTO.setPhoneNumber(trainer.getPhoneNumber());
        userDTO.setEmail(trainer.getEmail());
        userDTO.setBirth(trainer.getBirth().toString());
        userDTO.setRole(trainer.getRole().toString());
        userDTO.setActive(trainer.getActive());
        userDTO.setAverageGrade(trainer.getAverageGrade());

        return new ResponseEntity<>(userDTO.getId(), HttpStatus.OK);

    }

    @GetMapping(value = "/trainers/{adminId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getTrainers(@PathVariable Long adminId) {

        User admin = this.userService.findOne(adminId);
        if (admin == null || admin.getRole() != Role.Admin) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<User> userList = this.userService.findAll();

        List<UserDTO> userDTOS = new ArrayList<>();

        Boolean isDeleted = false;
        for (User user : userList) {
            UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName()
                    , user.getLastName(), user.getPhoneNumber(), user.getEmail(), user.getBirth().toString()
                    , user.getRole().toString(), user.getActive(), user.getAverageGrade(), isDeleted);
            if (user.getRole() == Role.Trainer && user.getIsDeleted() == Boolean.FALSE) {
                userDTOS.add(userDTO);
            }
        }

        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
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


    @PutMapping(value = "/delete/{adminId}/{trainerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long adminId, @PathVariable Long trainerId) throws Exception {

        User admin = this.userService.findOne(adminId);
        if (admin == null || admin.getRole() != Role.Admin) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.userService.delete(userService.findOne(trainerId));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/profile/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable Long userId) {

        User user = this.userService.findOne(userId);
        if (user == null || !user.getRole().equals(Role.User)) {
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
        userDTO.setIsDeleted(user.getIsDeleted());

        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

    @PutMapping(value = "/change/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> changeUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) throws Exception {

        User userCheck = this.userService.findOne(userId);
        if (userCheck == null || !userCheck.getRole().equals(Role.User)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = new User(userCheck.getUsername(), userDTO.getPassword(), userDTO.getFirstName(), userDTO.getLastName()
                , userDTO.getPhoneNumber(), userDTO.getEmail(), userCheck.getBirth(), userCheck.getRole(),
                userCheck.getActive(), userCheck.getAverageGrade(), userCheck.getIsDeleted());

        user.setId(userId);

        User changedUser = userService.change(user);

        UserDTO changedUserDTO = new UserDTO(changedUser.getId(), changedUser.getUsername(), changedUser.getPassword(),
                changedUser.getFirstName(), changedUser.getLastName(), changedUser.getPhoneNumber(), changedUser.getEmail(),
                changedUser.getBirth().toString(), changedUser.getRole().toString(), changedUser.getActive()
                , changedUser.getAverageGrade(), changedUser.getIsDeleted());

        return new ResponseEntity<>(changedUserDTO, HttpStatus.OK);
    }

}

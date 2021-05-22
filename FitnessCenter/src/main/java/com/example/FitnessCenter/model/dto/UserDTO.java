package com.example.FitnessCenter.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String birth;
    private String role;
    private Boolean active;
    private Double averageGrade;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String password, String firstName, String lastName, String phoneNumber, String email, String birth, String role, Boolean active, Double averageGrade) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birth = birth;
        this.role = role;
        this.active = active;
        this.averageGrade = averageGrade;
    }
}

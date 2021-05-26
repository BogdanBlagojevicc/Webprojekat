package com.example.FitnessCenter.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FitnessCenterDTO {

    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    public FitnessCenterDTO() {
    }

    public FitnessCenterDTO(Long id, String name, String address, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}

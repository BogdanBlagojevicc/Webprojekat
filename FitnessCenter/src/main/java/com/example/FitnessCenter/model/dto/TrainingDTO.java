package com.example.FitnessCenter.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainingDTO {

    private Long id;
    private String name;
    private String description;
    private String type;
    private Double duration;

    private UserDTO trainer;

    public TrainingDTO() {
    }

    public TrainingDTO(Long id, String name, String description, String type, Double duration, UserDTO trainer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.duration = duration;
        this.trainer = trainer;
    }

}

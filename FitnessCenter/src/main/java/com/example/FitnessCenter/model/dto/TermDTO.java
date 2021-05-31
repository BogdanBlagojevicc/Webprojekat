package com.example.FitnessCenter.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TermDTO {

    private Long id;
    private Double price;
    private String  start;
    private Integer number_of_applications;

    public TermDTO() {
    }

    public TermDTO(Long id, Double price, String start, Integer number_of_applications) {
        this.id = id;
        this.price = price;
        this.start = start;
        this.number_of_applications = number_of_applications;
    }
}

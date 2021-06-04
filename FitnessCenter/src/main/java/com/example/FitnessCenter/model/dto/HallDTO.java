package com.example.FitnessCenter.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HallDTO {
    private Long id;
    private Integer capacity;
    private String mark;

    public HallDTO() {
    }

    public HallDTO(Long id, Integer capacity, String mark) {
        this.id = id;
        this.capacity = capacity;
        this.mark = mark;
    }
}

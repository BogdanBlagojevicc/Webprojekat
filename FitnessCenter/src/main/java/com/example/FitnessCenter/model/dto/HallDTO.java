package com.example.FitnessCenter.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HallDTO {
    private Long id;
    private Integer capacity;
    private String mark;
    private Boolean isDeleted;

    public HallDTO() {
    }

    public HallDTO(Long id, Integer capacity, String mark, Boolean isDeleted) {
        this.id = id;
        this.capacity = capacity;
        this.mark = mark;
        this.isDeleted = isDeleted;
    }
}

package com.example.FitnessCenter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

enum Type {Hit, Cardio, TRX, Combat, Box, Gym}

@Entity
@Getter
@Setter
public class Training implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private com.example.FitnessCenter.model.dto.Type type;

    @Column
    private Double duration;

    @OneToMany(mappedBy = "training", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Term> terms;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User trainer;

    public Training() {
    }

    public Training(String name, String description, com.example.FitnessCenter.model.dto.Type type, Double duration) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.duration = duration;
    }

    public Training(String name, String description, com.example.FitnessCenter.model.dto.Type type, Double duration, User trainer) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.duration = duration;
        this.trainer = trainer;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", duration=" + duration +
                ", trainer=" + trainer +
                '}';
    }

}

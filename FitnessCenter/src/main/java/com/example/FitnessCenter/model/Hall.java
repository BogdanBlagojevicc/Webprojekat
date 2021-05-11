package com.example.FitnessCenter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer capatity;

    @Column(unique = true)
    private String mark;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private FitnessCenter fitnessCenter;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Term> week_schedule;




}

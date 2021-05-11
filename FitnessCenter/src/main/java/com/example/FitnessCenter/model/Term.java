package com.example.FitnessCenter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.print.attribute.standard.DateTimeAtCompleted;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double price;

    @Column             //mozda treba da se promeni
    private Date start;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Apply> term_apply;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private FitnessCenter fitnessCenter;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Hall hall;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Training training;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;
}

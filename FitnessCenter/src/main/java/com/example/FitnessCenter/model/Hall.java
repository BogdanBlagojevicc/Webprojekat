package com.example.FitnessCenter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class Hall implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer capacity;

    @Column(unique = true)
    private String mark;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private FitnessCenter fitnessCenter;

    @OneToMany(mappedBy = "hall", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Term> week_schedule;

    public Hall() {
    }

    public Hall(Integer capacity, String mark) {
        this.capacity = capacity;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", mark='" + mark + '\'' +
                ", fitnessCenter=" + fitnessCenter +
                '}';
    }
}

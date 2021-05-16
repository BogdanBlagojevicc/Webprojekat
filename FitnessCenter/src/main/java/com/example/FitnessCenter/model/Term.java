package com.example.FitnessCenter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Term implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double price;

    @Column
    private Date start;

    @Column
    private Integer number_of_applications;

    @OneToMany(mappedBy = "term", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Apply> term_apply;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Hall hall;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Training training;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User trainer;

    @Override
    public String toString() {
        return "Term{" +
                "id=" + id +
                ", price=" + price +
                ", start=" + start +
                ", number_of_applications=" + number_of_applications +
                ", hall=" + hall +
                ", training=" + training +
                ", trainer=" + trainer +
                '}';
    }
}

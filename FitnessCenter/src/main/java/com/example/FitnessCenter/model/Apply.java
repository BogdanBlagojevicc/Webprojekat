package com.example.FitnessCenter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Apply implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer grade;

    @Column
    private Boolean done;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User sports_man;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Term term;

    public Apply() {
    }

    public Apply(Integer grade, Boolean done) {
        this.grade = grade;
        this.done = done;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "id=" + id +
                ", grade=" + grade +
                ", done=" + done +
                ", sports_man=" + sports_man +
                ", term=" + term +
                '}';
    }
}

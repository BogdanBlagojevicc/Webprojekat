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

    @Column
    private Boolean isDeleted;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User sportists;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Term term;

    public Apply() {
    }

    public Apply(Integer grade, Boolean done, Boolean isDeleted) {
        this.grade = grade;
        this.done = done;
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "id=" + id +
                ", grade=" + grade +
                ", done=" + done +
                ", spostists=" + sportists +
                ", term=" + term +
                ", isDeleted=" + isDeleted +
                '}';
    }
}

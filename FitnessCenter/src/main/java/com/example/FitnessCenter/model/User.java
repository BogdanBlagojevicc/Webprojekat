package com.example.FitnessCenter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


enum Role {Admin, Trainer, User}
@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @Column
    private Date birth;

    @Column
    @Enumerated
    private Role role;

    @Column
    private Boolean active;

    @Column
    private Double averageGrade;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Apply> applications;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Training> trainings;

}

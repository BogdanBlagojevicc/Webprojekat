package com.example.FitnessCenter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


enum Role {Admin, Trainer, User}

@Entity
@Getter
@Setter
public class User implements Serializable {
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
    @Enumerated(EnumType.STRING)
    private com.example.FitnessCenter.model.dto.Role role;

    @Column
    private Boolean active;

    @Column
    private Double averageGrade;

    @Column
    private Boolean isDeleted;

    @OneToMany(mappedBy = "sportists", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Apply> applications;

    //za trenera koje treninge drzi
    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Training> trainings;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private FitnessCenter fitnessCenter;

    public User() {
    }

    public User(String username, String password, String firstName, String lastName, String phoneNumber, String email, Date birth, com.example.FitnessCenter.model.dto.Role role, Boolean active, Double averageGrade, Boolean isDeleted) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birth = birth;
        this.role = role;
        this.active = active;
        this.averageGrade = averageGrade;
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", role=" + role +
                ", active=" + active +
                ", averageGrade=" + averageGrade +
                ", fitnessCenter=" + fitnessCenter +
                ", isDeleted=" + isDeleted +
                '}';
    }
}

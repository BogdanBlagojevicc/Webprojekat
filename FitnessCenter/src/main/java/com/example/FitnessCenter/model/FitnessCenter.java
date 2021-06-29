package com.example.FitnessCenter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class FitnessCenter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @Column
    private Boolean isDeleted;

    @OneToMany(mappedBy = "fitnessCenter", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;

    @OneToMany(mappedBy = "fitnessCenter", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hall> halls;

    public FitnessCenter() {
    }

    public FitnessCenter(String name, String address, String phoneNumber, String email, Boolean  isDeleted) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "FitnessCenter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                '}';
    }
}

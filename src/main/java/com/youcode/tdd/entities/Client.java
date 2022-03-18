package com.youcode.tdd.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Client {

    @Id
    @SequenceGenerator(name = "client_seq", sequenceName = "client_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    private Long id;
    private String email;
    private String phone;
    private String fullName;
    private int age;
    private String gender;
    private boolean isActive;

    public Client() {
    }
}

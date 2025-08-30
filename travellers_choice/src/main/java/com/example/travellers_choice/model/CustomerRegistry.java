package com.example.travellers_choice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CustomerRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone;
    private String package_name;
    private String region;
    private String bdate;
    private String tdate;
    private int num_seats;
    private int num_adults;
    private int num_children;
    private String city;
    private String state;
    private String country;

}

package com.example.test28_09jpa.Test1;

import jakarta.persistence.*;

@Entity

@Table(name = "employees")

public class EmployeeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;

    private String name;

    private Integer age;

    private String address; }
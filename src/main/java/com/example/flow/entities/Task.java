package com.example.flow.entities;

import jakarta.persistence.*;

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
}

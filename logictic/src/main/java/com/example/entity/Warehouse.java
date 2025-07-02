package com.example.entity;

import jakarta.persistence.*;
@Table(name = "warehouses")
@Entity
public class Warehouse {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private int capacity;

    public Warehouse() {
    }

    public Warehouse(String name, String location, int capacity) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

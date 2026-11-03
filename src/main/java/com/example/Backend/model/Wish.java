package com.example.Backend.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * One person's answers from the four Diwali questions.
 */

@Document(collection = "wishes")
public class Wish {

    @Id
    private String id;

    private String name;

    private String cracker;

    private String sweet;

    private String food;

    @Indexed
    private Instant createdAt;

    public Wish() {
    }

    public Wish(String name, String cracker, String sweet, String food) {
        this.name = name;
        this.cracker = cracker;
        this.sweet = sweet;
        this.food = food;
        this.createdAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCracker() {
        return cracker;
    }

    public void setCracker(String cracker) {
        this.cracker = cracker;
    }

    public String getSweet() {
        return sweet;
    }

    public void setSweet(String sweet) {
        this.sweet = sweet;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
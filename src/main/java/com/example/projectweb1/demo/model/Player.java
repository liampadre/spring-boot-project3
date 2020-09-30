package com.example.projectweb1.demo.model;

public class Player {

    private String name;

    private String lastname;

    private int age;

    private String sport;

    public Player() {}

    public Player(String name, String lastname, int age, String sport) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.sport = sport;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}

package com.grace.biometricattendance.models;

public class Student {
    String id;
    String level;
    String gender;
    String firstName;
    String LastName;

    public Student(String id, String level, String gender, String firstName, String lastName) {
        this.id = id;
        this.level = level;
        this.gender = gender;
        this.firstName = firstName;
        LastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}

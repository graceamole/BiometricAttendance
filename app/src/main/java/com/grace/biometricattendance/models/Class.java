package com.grace.biometricattendance.models;

public class Class {
    String course_title;
    String course_code;
    String id;

    public Class(String course_title, String course_code,String userId) {
        this.id = userId;
        this.course_title = course_title;
        this.course_code = course_code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }
}

package com.grace.biometricattendance.models;

public class Class {
    String course_title;
    String course_code;

    public Class(String id, String course_title, String course_code) {
        this.course_title = course_title;
        this.course_code = course_code;
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

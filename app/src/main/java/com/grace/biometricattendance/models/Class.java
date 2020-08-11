package com.grace.biometricattendance.models;

import java.util.List;

public class Class {
    String course_title;
    String course_code;
    String id;
    List<Student> listOfStudents;
    String classId;

    public Class() {

    }


    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Class(String userId, String classId, String course_title, String course_code, List<Student> listOfStudents) {
        this.id = userId;
        this.course_title = course_title;
        this.classId = classId;
        this.course_code = course_code;
        this.listOfStudents = listOfStudents;

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
    public List<Student> getListOfStudents() {
        return listOfStudents;
    }

    public void setListOfStudents(List<Student> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }
}

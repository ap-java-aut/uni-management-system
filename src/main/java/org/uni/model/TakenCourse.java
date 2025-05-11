package org.uni.model;

import jakarta.persistence.*;
import org.uni.framework.UniEntity;

@Entity
@Table(name = "taken_courses")
public class TakenCourse extends UniEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "presented_course_id")
    private PresentedCourse presentedCourse;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(precision = 2)
    private Double grade;

    public TakenCourse() {
    }

    public TakenCourse(PresentedCourse presentedCourse, Student student) {
        this.presentedCourse = presentedCourse;
        this.student = student;
    }

    public PresentedCourse getPresentedCourse() {
        return presentedCourse;
    }

    public void setPresentedCourse(PresentedCourse presentedCourse) {
        this.presentedCourse = presentedCourse;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "TakenCourse{" +
                "presentedCourse=" + presentedCourse +
                ", student=" + student +
                ", grade=" + grade +
                ", id=" + id +
                '}';
    }
}

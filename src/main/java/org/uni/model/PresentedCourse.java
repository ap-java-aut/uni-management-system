package org.uni.model;

import jakarta.persistence.*;
import org.uni.framework.UniEntity;

@Entity
@Table(name = "presented_courses")
public class PresentedCourse extends UniEntity {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    public PresentedCourse() {
    }

    public PresentedCourse(Course course, Professor professor) {
        this.course = course;
        this.professor = professor;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "PresentedCourse{" +
                "course=" + course +
                ", professor=" + professor +
                ", id=" + id +
                '}';
    }
}

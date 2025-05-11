package org.uni.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.uni.framework.UniEntity;

@Entity
@Table(name = "courses")
public class Course extends UniEntity {
    @Basic(optional = false)
    @Column(length = 50)
    private String title;

    @Basic(optional = false)
    private Integer credits;

    public Course() {
    }

    public Course(String title, Integer credits) {
        this.title = title;
        this.credits = credits;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", credits=" + credits +
                ", id=" + id +
                '}';
    }
}

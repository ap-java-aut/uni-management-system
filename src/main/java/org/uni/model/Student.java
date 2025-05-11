package org.uni.model;

import jakarta.persistence.*;
import org.uni.framework.UniEntity;

@Entity
@Table(name = "students")
public class Student extends UniEntity {
    @Basic(optional = false)
    @Column(length = 8)
    private String Number;

    @OneToOne(optional = false)
    @JoinColumn(name = "person_id")
    private Person person;

    public Student() {
    }

    public Student(String number, Person person) {
        Number = number;
        this.person = person;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Number='" + Number + '\'' +
                ", person=" + person +
                ", id=" + id +
                '}';
    }
}

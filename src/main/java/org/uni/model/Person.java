package org.uni.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.uni.framework.UniEntity;

@Entity
@Table(name = "people")
public class Person extends UniEntity {
    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    @Column(name = "national_id", length = 10)
    private String nationalId;

    public Person() {
    }

    public Person(String name, String nationalId) {
        this.name = name;
        this.nationalId = nationalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", id=" + id +
                '}';
    }
}

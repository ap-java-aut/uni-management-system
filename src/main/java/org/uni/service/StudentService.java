package org.uni.service;

import org.uni.framework.ServiceBase;
import org.uni.model.Person;
import org.uni.model.Student;

import java.util.List;

public class StudentService extends ServiceBase<Student> {
    public StudentService() {
        super(Student.class);
    }

    public Student persist(String name, String nationalId, String number) {
        Person person = new Person(name, nationalId);
        return persist(person, number);
    }

    public Student persist(Person person, String number) {
        Student student = new Student(number, person);

        persist(student);

        return student;
    }

    public List<Student> fetchByName(String name) {
        return getSessionFactory().fromTransaction(session -> session.createQuery("select s from Student s " +
                        "join Person p on p.id = s.person.id " +
                        "where p.name like :name", Student.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList());
    }
}

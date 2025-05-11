package org.uni.service;

import org.uni.framework.ServiceBase;
import org.uni.model.Person;
import org.uni.model.Professor;
import org.uni.model.Student;

import java.util.List;

public class ProfessorService extends ServiceBase<Professor> {
    public ProfessorService() {
        super(Professor.class);
    }

    public Professor persist(String name, String nationalId, String number) {
        Person person = new Person(name, nationalId);
        return persist(person, number);
    }

    public Professor persist(Person person, String number) {
        Professor professor = new Professor(number, person);

        persist(professor);

        return professor;
    }

    public List<Professor> fetchByName(String name) {
        return getSessionFactory().fromTransaction(session -> session.createQuery("select prof from Professor prof " +
                        "join Person p on p.id = prof.person.id " +
                        "where p.name like :name", Professor.class)
                .setParameter("name", "%" + name + "%")
                .getResultList());
    }
}

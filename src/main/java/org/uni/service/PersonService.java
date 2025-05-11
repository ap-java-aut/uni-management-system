package org.uni.service;

import org.uni.framework.ServiceBase;
import org.uni.model.Person;

import java.util.List;

public class PersonService extends ServiceBase<Person> {
    public PersonService() {
        super(Person.class);
    }

    public Person persist(String name, String nationalId) {
        Person person = new Person(name, nationalId);
        persist(person);

        return person;
    }

    public List<Person> fetchByName(String name) {
        return getSessionFactory().fromTransaction(session -> session.createSelectionQuery("from Person where name like :name", Person.class)
                .setParameter("name", "%" + name + "%")
                .getResultList());
    }

    public Person fetchByNationalId(String nationalId) {
        return getSessionFactory().fromTransaction(session -> session.createSelectionQuery("from Person where nationalId like :nationalId", Person.class)
                .setParameter("nationalId", nationalId)
                .getSingleResultOrNull());
    }
}

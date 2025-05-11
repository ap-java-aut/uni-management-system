package org.uni.cli;

import jakarta.persistence.Subgraph;
import org.hibernate.graph.RootGraph;
import org.uni.model.*;
import org.uni.service.*;

import java.util.List;
import java.util.Scanner;

public class CLI {
    private Scanner scanner = new Scanner(System.in);

    private final static PersonService personService = new PersonService();
    private final static StudentService studentService = new StudentService();
    private final static ProfessorService professorService = new ProfessorService();
    private final static CourseService courseService = new CourseService();
    private final static PresentedCourseService presentedCourseService = new PresentedCourseService();
    private final static TakenCourseService takenCourseService = new TakenCourseService();

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("command (persist, fetch, remove, other, exit): ");
            String command = scanner.nextLine();

            switch (command) {
                case "persist":
                    persistCommand();
                    break;
                case "fetch":
                    fetchCommand();
                    break;
                case "remove":
                    removeCommand();
                    break;
                case "other":
                    otherCommand();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("invalid command.");
            }
        }
    }

    // <editor-fold desc="Commands">

    private void persistCommand() {
        System.out.print("entity (person, professor, student, course, presented course, taken course): ");
        String command = scanner.nextLine();

        switch (command) {
            case "person":
                persistPerson();
                break;
            case "student":
                persistStudent();
                break;
            case "professor":
                persistProfessor();
                break;
            case "course":
                persistCourse();
                break;
            case "presented course":
                persistPresentedCourse();
                break;
            case "taken course":
                persistTakenCourse();
                break;
            default:
                System.out.println("invalid entity.");
        }
    }

    private void fetchCommand() {
        System.out.print("entity (person, professor, student, course, presented course, taken course): ");
        String command = scanner.nextLine();

        switch (command) {
            case "person":
                fetchPerson();
                break;
            case "student":
                fetchStudent();
                break;
            case "professor":
                fetchProfessor();
                break;
            case "course":
                fetchCourse();
                break;
            case "presented course":
                fetchPresentedCourse();
                break;
            case "taken course":
                fetchTakenCourse();
                break;
            default:
                System.out.println("invalid entity.");
        }
    }

    private void removeCommand() {
        System.out.print("entity (person, professor, student, course, presented course, taken course): ");
        String command = scanner.nextLine();

        switch (command) {
            case "person":
                removePerson();
                break;
            case "student":
                removeStudent();
                break;
            case "professor":
                removeProfessor();
                break;
            case "course":
                removeCourse();
                break;
            case "presented course":
                removePresentedCourse();
                break;
            case "taken course":
                removeTakenCourse();
                break;
            default:
                System.out.println("invalid entity.");
        }
    }

    private void otherCommand() {
        System.out.print("entity (student, taken course): ");
        String command = scanner.nextLine();

        switch (command) {
            case "student":
                otherStudent();
                break;
            case "taken course":
                otherTakenCourse();
                break;
            default:
                System.out.println("invalid entity.");
        }
    }

    // </editor-fold>

    // <editor-fold desc="Person">

    private void persistPerson() {
        System.out.print("name: ");
        String name = scanner.nextLine();

        System.out.print("national id: ");
        String nationalId = scanner.nextLine();

        Person person = personService.persist(name, nationalId);

        System.out.println("successfully persisted person. id: " + person.getId());
    }

    private void fetchPerson() {
        System.out.print("by (all, id, name): ");
        String by = scanner.nextLine();

        switch (by) {
            case "all":
                fetchAllPeople();
                break;
            case "id":
                fetchPersonById();
                break;
            case "name":
                fetchPersonByName();
                break;
            default:
                System.out.println("invalid option.");
        }
    }

    private void fetchPersonById() {
        System.out.print("id: ");
        int id = Integer.parseInt(scanner.nextLine());

        Person person = personService.fetchById(id);

        if (person == null) {
            System.out.println("person not found.");
        } else {
            System.out.println(person);
        }
    }

    private void fetchAllPeople() {
        List<Person> people = personService.fetchAll();

        if (people.isEmpty()) {
            System.out.println("no people found.");
            return;
        }

        for (Person person : people) {
            System.out.println(person);
        }
    }

    private void fetchPersonByName() {
        System.out.print("name: ");
        String name = scanner.nextLine();

        List<Person> people = personService.fetchByName(name);

        for (Person person : people) {
            System.out.println(person);
        }
    }

    private void removePerson() {
        System.out.print("id: ");
        int id = Integer.parseInt(scanner.nextLine());

        personService.remove(id);

        System.out.println("successfully removed person.");
    }

    // </editor-fold>

    // <editor-fold desc="Student">

    private void persistStudent() {
        System.out.print("person id: ");
        int personId = Integer.parseInt(scanner.nextLine());

        System.out.print("number: ");
        String number = scanner.nextLine();

        Person person = personService.fetchById(personId);

        Student student = studentService.persist(person, number);

        System.out.println("successfully persisted student. id: " + student.getId());
    }

    private void fetchStudent() {
        System.out.print("by (all, id, name): ");
        String by = scanner.nextLine();

        switch (by) {
            case "all":
                fetchAllStudents();
                break;
            case "id":
                fetchStudentById();
                break;
            case "name":
                fetchStudentByName();
                break;
            default:
                System.out.println("invalid option.");
        }
    }

    private void fetchStudentById() {
        System.out.print("id: ");
        int id = Integer.parseInt(scanner.nextLine());

        Student student = studentService.fetchById(id);

        if (student == null) {
            System.out.println("person not found.");
        } else {
            System.out.println(student);
        }
    }

    private void fetchAllStudents() {
        List<Student> students = studentService.fetchAll();

        if (students.isEmpty()) {
            System.out.println("no people found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void fetchStudentByName() {
        System.out.print("name: ");
        String name = scanner.nextLine();

        List<Student> students = studentService.fetchByName(name);

        if (students.isEmpty()) {
            System.out.println("no students found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void removeStudent() {
        System.out.print("id: ");
        int id = Integer.parseInt(scanner.nextLine());

        studentService.remove(id);

        System.out.println("successfully removed student.");
    }

    private void otherStudent() {
        System.out.print("command (calc grade): ");
        String command = scanner.nextLine();

        switch (command) {
            case "calc grade":
                calcStudentGrade();
                break;
            default:
                System.out.println("invalid command.");
        }
    }

    private void calcStudentGrade() {
        System.out.print("student id: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        double grade = takenCourseService.getStudentGrade(studentId);

        System.out.println("student grade: " + grade);
    }

    // </editor-fold>

    // <editor-fold desc="Professor">

    private void persistProfessor() {
        System.out.print("person id: ");
        int personId = Integer.parseInt(scanner.nextLine());

        System.out.print("number: ");
        String number = scanner.nextLine();

        Person person = personService.fetchById(personId);

        Professor prof = professorService.persist(person, number);

        System.out.println("successfully persisted professor. id: " + prof.getId());
    }

    private void fetchProfessor() {
        System.out.print("by (all, id, name): ");
        String by = scanner.nextLine();

        switch (by) {
            case "all":
                fetchAllProfessors();
                break;
            case "id":
                fetchProfessorById();
                break;
            case "name":
                fetchProfessorByName();
                break;
            default:
                System.out.println("invalid option.");
        }
    }

    private void fetchProfessorById() {
        System.out.print("id: ");
        int id = Integer.parseInt(scanner.nextLine());

        Professor prof = professorService.fetchById(id);

        if (prof == null) {
            System.out.println("person not found.");
        } else {
            System.out.println(prof);
        }
    }

    private void fetchAllProfessors() {
        List<Professor> professors = professorService.fetchAll();

        if (professors.isEmpty()) {
            System.out.println("no people found.");
            return;
        }

        for (Professor professor : professors) {
            System.out.println(professor);
        }
    }

    private void fetchProfessorByName() {
        System.out.print("name: ");
        String name = scanner.nextLine();

        List<Professor> professors = professorService.fetchByName(name);

        if (professors.isEmpty()) {
            System.out.println("no students found.");
            return;
        }

        for (Professor prof : professors) {
            System.out.println(prof);
        }
    }

    private void removeProfessor() {
        System.out.print("id: ");
        int id = Integer.parseInt(scanner.nextLine());

        professorService.remove(id);

        System.out.println("successfully removed professor.");
    }

    // </editor-fold>

    // <editor-fold desc="Course">

    private void persistCourse() {
        System.out.print("name: ");
        String name = scanner.nextLine();

        System.out.print("credits: ");
        int credits = Integer.parseInt(scanner.nextLine());

        Course course = courseService.persist(name, credits);

        System.out.println("successfully persisted course. id: " + course.getId());
    }

    private void fetchCourse() {
        System.out.print("by (all, id, title): ");
        String by = scanner.nextLine();

        switch (by) {
            case "all":
                fetchAllCourses();
                break;
            case "id":
                fetchCourseById();
                break;
            case "title":
                fetchCourseByTitle();
                break;
            default:
                System.out.println("invalid option.");
        }
    }

    private void fetchAllCourses() {
        List<Course> courses = courseService.fetchAll();

        if (courses.isEmpty()) {
            System.out.println("no courses found.");
            return;
        }

        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private void fetchCourseById() {
        System.out.print("id: ");
        int id = Integer.parseInt(scanner.nextLine());

        Course course = courseService.fetchById(id);

        if (course == null) {
            System.out.println("course not found.");
            return;
        }

        System.out.println(course);
    }

    private void fetchCourseByTitle() {
        System.out.print("title: ");
        String title = scanner.nextLine();

        List<Course> courses = courseService.fetchCourseByTitle(title);

        if (courses.isEmpty()) {
            System.out.println("no courses found.");
            return;
        }

        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private void removeCourse() {
        System.out.print("id: ");
        int id = Integer.parseInt(scanner.nextLine());

        courseService.remove(id);

        System.out.println("successfully removed course.");
    }

    // </editor-fold>

    // <editor-fold desc="Presented Course">

    private void persistPresentedCourse() {
        System.out.print("course id: ");
        int courseId = Integer.parseInt(scanner.nextLine());

        System.out.print("professor id: ");
        int professorId = Integer.parseInt(scanner.nextLine());

        Course course = courseService.fetchRefById(courseId);
        Professor prof = professorService.fetchRefById(professorId);

        PresentedCourse presentedCourse = presentedCourseService.persist(course, prof);

        System.out.println("successfully persisted presented course. id: " + presentedCourse.getId());
    }

    private void fetchPresentedCourse() {
        System.out.print("by (all, id, title): ");
        String by = scanner.nextLine();

        switch (by) {
            case "all":
                fetchAllPresentedCourses();
                break;
            case "id":
                fetchPresentedCourseById();
                break;
            case "title":
                fetchPresentedCourseByTitle();
                break;
            default:
                System.out.println("invalid option.");
        }
    }

    private void fetchAllPresentedCourses() {
        List<PresentedCourse> presentedCourses = presentedCourseService.fetchAll(session -> {
            RootGraph<PresentedCourse> graph = session.createEntityGraph(PresentedCourse.class);
            graph.addAttributeNodes("course", "professor");

            Subgraph<Professor> profGraph = graph.addSubgraph("professor");
            profGraph.addAttributeNodes("person");

            return graph;
        });

        if (presentedCourses.isEmpty()) {
            System.out.println("no presented courses found.");
            return;
        }

        for (PresentedCourse presentedCourse : presentedCourses) {
            System.out.println(presentedCourse);
        }
    }

    public void fetchPresentedCourseById() {
        System.out.print("id: ");
        int id = Integer.parseInt(scanner.nextLine());

        PresentedCourse presentedCourse = presentedCourseService.fetchById(id, session -> {
            RootGraph<PresentedCourse> graph = session.createEntityGraph(PresentedCourse.class);
            graph.addAttributeNodes("course", "professor");

            Subgraph<Professor> profGraph = graph.addSubgraph("professor");
            profGraph.addAttributeNodes("person");

            return graph;
        });

        if (presentedCourse == null) {
            System.out.println("presented course not found.");
            return;
        }

        System.out.println(presentedCourse);
    }

    public void fetchPresentedCourseByTitle() {
        System.out.print("title: ");
        String title = scanner.nextLine();

        List<PresentedCourse> presentedCourses = presentedCourseService.fetchAllByCourseTitle(title, session -> {
            RootGraph<PresentedCourse> graph = session.createEntityGraph(PresentedCourse.class);
            graph.addAttributeNodes("course", "professor");

            Subgraph<Professor> profGraph = graph.addSubgraph("professor");
            profGraph.addAttributeNodes("person");

            return graph;
        });

        if (presentedCourses.isEmpty()) {
            System.out.println("no presented course found.");
            return;
        }

        for (PresentedCourse presentedCourse : presentedCourses) {
            System.out.println(presentedCourse);
        }
    }

    private void removePresentedCourse() {
        System.out.print("id: ");
        int id = Integer.parseInt(scanner.nextLine());

        presentedCourseService.remove(id);

        System.out.println("successfully removed presented course.");
    }

    // </editor-fold>

    // <editor-fold desc="Taken Course">

    private void persistTakenCourse() {
        System.out.print("presented course id: ");
        int presentedCourseId = Integer.parseInt(scanner.nextLine());

        System.out.print("student id: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        PresentedCourse presentedCourse = presentedCourseService.fetchRefById(presentedCourseId);
        Student student = studentService.fetchRefById(studentId);

        TakenCourse takenCourse = takenCourseService.persist(presentedCourse, student);

        System.out.println("successfully persisted taken course. id: " + takenCourse.getId());
    }

    private void fetchTakenCourse() {
        System.out.print("by (all, id, student id): ");
        String by = scanner.nextLine();

        switch (by) {
            case "all":
                fetchAllTakenCourses();
                break;
            case "id":
                fetchTakenCourseById();
                break;
            case "student id":
                fetchTakenCourseByStudentId();
                break;
            default:
                System.out.println("invalid option.");
        }
    }

    private void fetchAllTakenCourses() {
        List<TakenCourse> takenCourses = takenCourseService.fetchAll(session -> {
            RootGraph<TakenCourse> graph = session.createEntityGraph(TakenCourse.class);
            graph.addAttributeNodes("student", "presentedCourse");

            Subgraph<PresentedCourse> presentedCourseSubgraph = graph.addSubgraph("presentedCourse");
            presentedCourseSubgraph.addAttributeNodes("course", "professor");

            return graph;
        });

        if (takenCourses.isEmpty()) {
            System.out.println("no taken courses found.");
            return;
        }

        for (TakenCourse takenCourse : takenCourses) {
            System.out.println(takenCourse);
        }
    }

    private void fetchTakenCourseById() {
        System.out.print("id: ");
        int id = Integer.parseInt(scanner.nextLine());

        TakenCourse takenCourse = takenCourseService.fetchById(id, session -> {
            RootGraph<TakenCourse> graph = session.createEntityGraph(TakenCourse.class);
            graph.addAttributeNodes("student", "presentedCourse");

            Subgraph<PresentedCourse> presentedCourseSubgraph = graph.addSubgraph("presentedCourse");
            presentedCourseSubgraph.addAttributeNodes("course", "professor");

            return graph;
        });

        if (takenCourse == null) {
            System.out.println("taken course not found.");
            return;
        }

        System.out.println(takenCourse);
    }

    private void fetchTakenCourseByStudentId() {
        System.out.print("student id: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        List<TakenCourse> takenCourses = takenCourseService.fetchTakenCoursesForStudent(studentId, session -> {
            RootGraph<TakenCourse> graph = session.createEntityGraph(TakenCourse.class);
            graph.addAttributeNodes("student", "presentedCourse");

            Subgraph<PresentedCourse> presentedCourseSubgraph = graph.addSubgraph("presentedCourse");
            presentedCourseSubgraph.addAttributeNodes("course", "professor");

            return graph;
        });

        if (takenCourses.isEmpty()) {
            System.out.println("no taken courses found.");
            return;
        }

        for (TakenCourse takenCourse : takenCourses) {
            System.out.println(takenCourse);
        }
    }

    private void removeTakenCourse() {
        System.out.print("id: ");
        int id = Integer.parseInt(scanner.nextLine());

        takenCourseService.remove(id);

        System.out.println("successfully removed taken course.");
    }

    private void otherTakenCourse() {
        System.out.print("command (set grade): ");
        String command = scanner.nextLine();

        switch (command) {
            case "set grade":
                setGradeOfTakenCourse();
                break;
            default:
                System.out.println("invalid command.");
        }
    }

    private void setGradeOfTakenCourse() {
        System.out.print("taken course id: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("grade: ");
        double grade = Double.parseDouble(scanner.nextLine());

        takenCourseService.setTakenCourseGrade(id, grade);

        System.out.println("grade set successfully.");
    }

    // </editor-fold>
}

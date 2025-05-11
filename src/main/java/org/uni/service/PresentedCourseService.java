package org.uni.service;

import org.hibernate.Session;
import org.hibernate.graph.RootGraph;
import org.uni.framework.ServiceBase;
import org.uni.model.Course;
import org.uni.model.PresentedCourse;
import org.uni.model.Professor;

import java.util.List;
import java.util.function.Function;

public class PresentedCourseService extends ServiceBase<PresentedCourse> {
    public PresentedCourseService() {
        super(PresentedCourse.class);
    }

    public PresentedCourse persist(Course course, Professor professor) {
        PresentedCourse presentedCourse = new PresentedCourse(course, professor);

        persist(presentedCourse);

        return presentedCourse;
    }

    public List<PresentedCourse> fetchAllByCourseTitle(String courseTitle) {
        return getSessionFactory().fromTransaction(session -> session.createQuery("select pc from PresentedCourse pc " +
                "join Course c on pc.course.id = c.id " +
                "where c.title like :courseTitle", PresentedCourse.class)
                .setParameter("courseTitle", "%" + courseTitle + "%")
                .getResultList());
    }

    public List<PresentedCourse> fetchAllByCourseTitle(String courseTitle, Function<Session, RootGraph<PresentedCourse>> graphCreator) {
        return getSessionFactory().fromTransaction(session -> session.createQuery("select pc from PresentedCourse pc " +
                        "join Course c on pc.course.id = c.id " +
                        "where c.title like :courseTitle", PresentedCourse.class)
                .setParameter("courseTitle", "%" + courseTitle + "%")
                .applyLoadGraph(graphCreator.apply(session))
                .getResultList());
    }
}

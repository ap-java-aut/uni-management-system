package org.uni.service;

import org.uni.framework.ServiceBase;
import org.uni.model.Course;

import java.util.List;

public class CourseService extends ServiceBase<Course> {
    public CourseService() {
        super(Course.class);
    }

    public Course persist(String title, int credit) {
        Course course = new Course(title, credit);
        persist(course);

        return course;
    }

    public List<Course> fetchCourseByTitle(String title) {
        return getSessionFactory().fromTransaction(session ->
                session.createSelectionQuery("from Course where title like :title", Course.class)
                .setParameter("title", "%" + title + "%")
                .getResultList());
    }
}

package org.uni.service;

import org.hibernate.Session;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.graph.RootGraph;
import org.uni.framework.ServiceBase;
import org.uni.model.PresentedCourse;
import org.uni.model.Student;
import org.uni.model.TakenCourse;

import java.util.List;
import java.util.function.Function;

public class TakenCourseService extends ServiceBase<TakenCourse> {
    public TakenCourseService() {
        super(TakenCourse.class);
    }

    public TakenCourse persist(PresentedCourse presentedCourse, Student student) {
        TakenCourse takenCourse = new TakenCourse(presentedCourse, student);

        persist(takenCourse);

        return takenCourse;
    }

    public List<TakenCourse> fetchTakenCoursesForStudent(int studentId) {
        return getSessionFactory().fromTransaction(session -> session.createSelectionQuery("from TakenCourse " +
                "where student.id = :studentId", TakenCourse.class)
                .setParameter("studentId", studentId)
                .getResultList());
    }

    public List<TakenCourse> fetchTakenCoursesForStudent(int studentId, Function<Session, RootGraph<TakenCourse>> graphCreator) {
        return getSessionFactory().fromTransaction(session -> session.createSelectionQuery("from TakenCourse " +
                        "where student.id = :studentId", TakenCourse.class)
                .setParameter("studentId", studentId)
                .setEntityGraph(graphCreator.apply(session), GraphSemantic.LOAD)
                .getResultList());
    }

    public void setTakenCourseGrade(int takenCourseId, double grade) {
        getSessionFactory().inTransaction(session -> session.createNativeMutationQuery("update taken_courses " +
                "set grade = :grade " +
                "where id = :takenCourseId")
                .setParameter("grade", grade)
                .setParameter("takenCourseId", takenCourseId)
                .executeUpdate());
    }

    public double getStudentGrade(int studentId) {
        return getSessionFactory().fromTransaction(session -> session.createNativeQuery("select sum(tc.grade * c.credits) / sum(c.credits) from taken_courses tc " +
                        "join presented_courses pc on tc.presented_course_id = pc.id " +
                        "join courses c on pc.course_id = c.id " +
                        "where tc.student_id = :studentId and tc.grade is not null", Double.class)
                .setParameter("studentId", studentId)
                .uniqueResult());
    }
}

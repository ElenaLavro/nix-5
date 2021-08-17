package com.company.initializer;

import com.company.model.entity.*;
import com.company.util.SessionFactoryUtil;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.LocalTime;

public class InitData {
    private static final Session session = SessionFactoryUtil.getSessionFactory().openSession();

    public static void initData() {
        try {
            session.beginTransaction();

            Course course = new Course("Java");
            Teacher teacher = new Teacher("Teacher1", course);
            Teacher teacher2 = new Teacher("Teacher2", course);

            Group group = new Group("Group 1", course, teacher);
            Group group2 = new Group("Group 2", course, teacher2);

            Student student1 = new Student("Student1", group, course);
            Student student2 = new Student("Student2", group2, course);

            Topic topic = new Topic("JPA.Hibernate", course);
            Topic topic2 = new Topic("SQL", course);

            Lesson lesson = new Lesson(LocalDate.of(2021, 1, 1), LocalTime.of(19, 0), topic);
            Lesson lesson2 = new Lesson(LocalDate.of(2021, 1, 4), LocalTime.of(19, 0), topic2);

            Mark mark1 = new Mark(9, student1, lesson);
            Mark mark2 = new Mark(10, student2, lesson);
            Mark mark3 = new Mark(9, student1, lesson2);
            Mark mark4 = new Mark(8, student2, lesson);

            session.persist(teacher);
            session.persist(teacher2);
            session.persist(course);
            session.persist(group);
            session.persist(group2);
            session.persist(student1);
            session.persist(student2);
            session.persist(topic);
            session.persist(topic2);
            session.persist(lesson);
            session.persist(lesson2);
            session.persist(mark1);
            session.persist(mark2);
            session.persist(mark3);
            session.persist(mark4);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}

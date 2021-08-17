package com.company.service.impl;

import com.company.model.dto.LessonDTO;
import com.company.model.entity.Course;
import com.company.model.entity.Lesson;
import com.company.model.entity.Teacher;
import com.company.model.entity.Topic;
import com.company.service.LessonService;
import com.company.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;

public class LessonServiceImpl implements LessonService {
    private final Session session = SessionFactoryUtil.getSessionFactory().openSession();

    @Override
    public LessonDTO findNearestLesson(Long id) throws SQLException {
        try {
            session.beginTransaction();

            Course course = getCourseByStudentID(id);
            Teacher teacher = getTeacherByStudentID(id);
            Lesson lesson = session.createQuery("from Lesson l where l.topic.course.id = :id order by l.date, l.time  asc ", Lesson.class).setParameter("id", course.getId()).setMaxResults(1).getSingleResult();
            Topic topic = session.find(Topic.class, lesson.getTopic().getId());
            session.getTransaction().commit();
            session.close();
            return new LessonDTO(lesson.getId(), lesson.getDate(), lesson.getTime(), teacher.getName(), topic.getTitle());
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
            throw new SQLException("Data exception");
        }
    }

    private Course getCourseByStudentID(Long id) {
        Query<Course> courseQuery = session.createQuery("select course " +
                "from Group g where g.id = (select group.id from Student s " +
                "where s.id = :id) ", Course.class);
        courseQuery.setParameter("id", id);
        return courseQuery.getSingleResult();
    }

    private Teacher getTeacherByStudentID(Long id) {
        Query<Teacher> teacherQuery = session.createQuery("select teacher from Group g " +
                "where g.id = (select group.id from Student s where s.id = :id)", Teacher.class);
        teacherQuery.setParameter("id", id);
        return teacherQuery.getSingleResult();
    }
}

package com.company;

import com.company.initializer.InitData;
import com.company.model.dto.LessonDTO;
import com.company.service.LessonService;
import com.company.service.impl.LessonServiceImpl;

import java.sql.SQLException;

public class HibernateMain {
    private static final LessonService lesson = new LessonServiceImpl();

    public static void main(String[] args) {
        InitData.initData();
        try {
            LessonDTO nearestLesson = lesson.findNearestLesson(1L);
            System.out.println("Nearest lesson by student 1:");
            System.out.println(nearestLesson);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

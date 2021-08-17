package com.company.service;

import com.company.model.dto.LessonDTO;

import java.sql.SQLException;

public interface LessonService {
    LessonDTO findNearestLesson(Long id) throws SQLException;
}

package com.company.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class LessonDTO {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String teacherName;
    private String topic;

    public LessonDTO() {
    }

    public LessonDTO(Long id, LocalDate date, LocalTime time, String teacherName, String topic) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.teacherName = teacherName;
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "LessonDTO{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", teacherName='" + teacherName + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}

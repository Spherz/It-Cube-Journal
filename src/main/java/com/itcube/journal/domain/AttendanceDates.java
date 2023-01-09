package com.itcube.journal.domain;

import javax.persistence.*;

@Entity
public class AttendanceDates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_student")
    private Students students;

    private String lessonDate;

    public AttendanceDates() {
    }

    public AttendanceDates(Integer id, String lessonDate, Students students) {
        this.id = id;
        this.lessonDate = lessonDate;
        this.students = students;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }
}

package com.itcube.journal.domain;

import javax.persistence.*;

@Entity
public class AttendanceDates {

    //TODO: Сделать привязку дат к группам
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String lessonDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_group")
    private Groups groups;

    public AttendanceDates() {
    }

    public AttendanceDates(Integer id, String lessonDate, Students students) {
        this.id = id;
        this.lessonDate = lessonDate;
    }

    public AttendanceDates(Integer id, String lessonDate, Students students, Groups groups) {
        this.id = id;
        this.lessonDate = lessonDate;
        this.groups = groups;
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

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
}

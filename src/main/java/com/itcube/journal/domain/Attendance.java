package com.itcube.journal.domain;

import javax.persistence.*;
import java.util.Set;

// TODO: Попробовать сделать фильтрацию dropdown все это на одной какой-то странице как (Список пользователей)
// То, что натыкают в этих dropdown потом сохраниться в таблице с посещаемостью

@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_student")
    private Students students;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_group")
    private Groups groups;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_date")
    private AttendanceDates date;

    public Attendance() {

    }

    public Attendance(Integer id, Students students, Groups groups, AttendanceDates date, String mark) {
        this.id = id;
        this.students = students;
        this.groups = groups;
        this.date = date;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudents() {
        return students != null ? students.getSurname() + " " + students.getFirstname() +
                " " + students.getSecondname() : "<none>";
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public String getGroups() {
        return groups != null ? groups.getGroupName() : "<none>";
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public String getDate() {
        return date != null ? date.getLessonDate() : "<none>";
    }

    public void setDate(AttendanceDates date) {
        this.date = date;
    }

}

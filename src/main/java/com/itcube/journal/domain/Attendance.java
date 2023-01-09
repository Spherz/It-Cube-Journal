package com.itcube.journal.domain;

import javax.persistence.*;
import java.util.Set;

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

    @ElementCollection(targetClass = Marks.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "students_marks", joinColumns = @JoinColumn(name = "student_id"))
    @Enumerated(EnumType.STRING)
    private Set<Marks> mark;

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

    public Set<Marks> getMark() {
        return mark;
    }

    public void setMark(Set<Marks> marks) {
        this.mark = marks;
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

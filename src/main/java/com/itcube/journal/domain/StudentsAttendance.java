package com.itcube.journal.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentsAttendance implements Serializable {

    public StudentsAttendance(Students student, AttendanceDates date, String mark) {
        this.students = student;
        this.dates = date;
        this.mark = mark;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "date_id")
    private AttendanceDates dates;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Students students;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groups groups;

    private String mark;
}

package com.itcube.journal.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentsAttendance implements Serializable {

    public StudentsAttendance(Students student, AttendanceDates date, String mark) {
        this.dates = date;
        this.mark = mark;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "date_id")
    private AttendanceDates dates;

    private String mark;
}

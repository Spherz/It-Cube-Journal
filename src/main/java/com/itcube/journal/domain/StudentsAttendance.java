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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "date_id")
    private AttendanceDates dates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_student")
    private Students student;

    private String mark;
}

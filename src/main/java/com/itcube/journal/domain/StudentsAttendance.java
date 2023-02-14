package com.itcube.journal.domain;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentsAttendance implements Serializable {

    @ManyToOne
    @JoinColumn(name = "date_id")
    private AttendanceDates dates;

    private String mark;
}

package com.itcube.journal.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

// TODO: Попробовать сделать фильтрацию dropdown все это на одной какой-то странице как (Список пользователей)
// То, что натыкают в этих dropdown потом сохраниться в таблице с посещаемостью

@Entity
@Table(name = "attendance")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Students students;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Groups groups;

    private LocalDate attendanceDate;

    private boolean isPresent;

    public Attendance(Students students, LocalDate attendanceDate, boolean isPresent) {
        this.students = students;
        this.attendanceDate = attendanceDate;
        this.isPresent = isPresent;
    }
}

package com.itcube.journal.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String courseName;
}

package com.itcube.journal.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "themes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Themes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String date;
    private String hoursCount;
    private String themeName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_group")
    private Groups groups;

}

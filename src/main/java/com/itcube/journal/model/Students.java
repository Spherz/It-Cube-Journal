package com.itcube.journal.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "students")
public class Students implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_group")
    private Groups nameGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_course")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_staff")
    private Staff staff;

    private String firstname;

    private String dateOfBirth;

    private String certificateNumber;

    private String parent;

    private String surname;

    private String secondname;

    private String studentClass;

    private String school;

    private String phoneNumber;

    private String email;

    public String getNameGroup() {
        return nameGroup != null ? nameGroup.getGroupName() : "<none>";
    }

    public String getStaff() {
        return staff != null ? staff.getSurname() + " " + staff.getFirstname() + " " + staff.getSecondname() : "<none>";
    }
}

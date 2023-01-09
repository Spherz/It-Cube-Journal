package com.itcube.journal.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_nickname")
    private User nickname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Groups teacherGroups;

    private String firstname;

    private String surname;

    private String secondname;

    private String birthDate;

    private String education;

    private String diplomaNumber;

    private String qualification;
    // Список групп

    public Staff() {
    }

    public Staff(Integer id, String firstname,
                 String surname, String secondname,
                 String birthDate, String education,
                 String diplomaNumber, String qualification, User teacherNickname, Groups groupsTeacher) {
        this.id = id;
        this.nickname = teacherNickname;
        this.teacherGroups = groupsTeacher;
        this.firstname = firstname;
        this.surname = surname;
        this.secondname = secondname;
        this.birthDate = birthDate;
        this.education = education;
        this.diplomaNumber = diplomaNumber;
        this.qualification = qualification;
    }

    public Staff(String surname, String firstname,
                 String secondname, String dateOfBirth,
                 String education, String diplomaNumber,
                 String qualification) {
        this.surname = surname;
        this.firstname = firstname;
        this.secondname = secondname;
        this.birthDate = dateOfBirth;
        this.education = education;
        this.diplomaNumber = diplomaNumber;
        this.qualification = qualification;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getDiplomaNumber() {
        return diplomaNumber;
    }

    public void setDiplomaNumber(String diplomaNumber) {
        this.diplomaNumber = diplomaNumber;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getTeacherNickname() {
        return nickname != null ? nickname.getUsername() : "<none>";
    }

    public String getNickname() {
        return nickname != null ? nickname.getUsername() : "<none>";
    }

    public void setNickname(User nickname) {
        this.nickname = nickname;
    }

    public Groups getTeacherGroups() {
        return teacherGroups;
    }

    public void setTeacherGroups(Groups teacherGroups) {
        this.teacherGroups = teacherGroups;
    }
}

package com.itcube.journal.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "students")
public class Students implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_group")
    private Groups nameGroup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_course")
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_staff")
    private Staff staff;

    @OneToMany(cascade = ALL, mappedBy = "student")
//    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private List<StudentsAttendance> attendance;

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

    public Students() {
    }

    public Students(Integer id, String surname, String firstname, String secondname, List<StudentsAttendance> attendance) {
        this.id = id;
        this.surname = surname;
        this.firstname = firstname;
        this.secondname = secondname;
        this.attendance = attendance;
    }

    public Students(Integer id, String firstname,
                    String surname, String secondname,
                    String studentClass, String school,
                    String phoneNumber, String email,
                    Groups studentsByGroup, Staff studentsByStaff)
    {
        this.id = id;
        this.nameGroup = studentsByGroup;
        this.staff = studentsByStaff;
        this.firstname = firstname;
        this.surname = surname;
        this.secondname = secondname;
        this.studentClass = studentClass;
        this.school = school;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Students(String surname, String firstname, String secondname,
                    String birthDate, String certificate, String studentClass,
                    String parentFullName, String school, String phoneNumber, String email) {
        this.surname = surname;
        this.firstname = firstname;
        this.secondname = secondname;
        this.dateOfBirth = birthDate;
        this.certificateNumber = certificate;
        this.studentClass = studentClass;
        this.parent = parentFullName;
        this.school = school;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getNameGroup() {
        return nameGroup != null ? nameGroup.getGroupName() : "<none>";
    }

    public String getStaff() {
        return staff != null ? staff.getSurname() + " " + staff.getFirstname() + " " + staff.getSecondname() : "<none>";
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<StudentsAttendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<StudentsAttendance> mark) {
        this.attendance = mark;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void setNameGroup(Groups nameGroup) {
        this.nameGroup = nameGroup;
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

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}

package com.itcube.journal.domain;

import javax.persistence.*;

@Entity
@Table(name = "student_groups")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String groupName;

    private String programName;

    private String hours;

    private String teacher;

    private String decreeNumber;

    private String decreeDate;

    private String educationForm;
    // add Staff
    public Groups() {
    }

    public Groups(Integer id, String groupName,
                  String programName, String hours,
                  String teacher, String decreeNumber,
                  String decreeDate, String educationForm) {
        this.id = id;
        this.groupName = groupName;
        this.programName = programName;
        this.hours = hours;
        this.teacher = teacher;
        this.decreeNumber = decreeNumber;
        this.decreeDate = decreeDate;
        this.educationForm = educationForm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String group_name) {
        this.groupName = group_name;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String program_name) {
        this.programName = program_name;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDecreeNumber() {
        return decreeNumber;
    }

    public void setDecreeNumber(String decree_number) {
        this.decreeNumber = decree_number;
    }

    public String getDecreeDate() {
        return decreeDate;
    }

    public void setDecreeDate(String decree_date) {
        this.decreeDate = decree_date;
    }

    public String getEducationForm() {
        return educationForm;
    }

    public void setEducationForm(String education_form) {
        this.educationForm = education_form;
    }
}

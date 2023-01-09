package com.itcube.journal.domain;

import javax.persistence.*;

@Entity
@Table(name = "thematic_planning")
public class ThematicPlanning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String themeName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_staff")
    private Staff staff;

    private String course;

    private String totalHours;

    // TODO: Добавить связь с пользователями
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    public ThematicPlanning() {
    }

    public ThematicPlanning(String themeName, Staff staffByTheme,
                            String course, String totalHours, User user) {
        this.themeName = themeName;
        this.user = user;
        this.staff = staffByTheme;
        this.course = course;
        this.totalHours = totalHours;
    }

    public ThematicPlanning(String themeName,
                            String course, String totalHours) {
        this.themeName = themeName;
        this.course = course;
        this.totalHours = totalHours;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getThemeName() {
        return themeName;
    }

    public String getStaff() {
        return staff != null ? staff.getSurname() + " " + staff.getFirstname() + " " + staff.getSecondname() : "<none>";
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
}

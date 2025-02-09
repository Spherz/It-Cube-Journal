package com.itcube.journal.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

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
import java.util.Objects;

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
    @JsonBackReference
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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Students students = (Students) o;
        return getId() != null && Objects.equals(getId(), students.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

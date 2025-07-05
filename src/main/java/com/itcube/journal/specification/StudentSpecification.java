package com.itcube.journal.specification;

import com.itcube.journal.model.Course;
import com.itcube.journal.model.Group;
import com.itcube.journal.model.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class StudentSpecification {

    public Specification<Student> searchByKeyword(String keyword) {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return buildPredicate(root, criteriaBuilder, keyword);
            }
        };
    }

    private Predicate buildPredicate(Root<Student> root, CriteriaBuilder criteriaBuilder, String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return criteriaBuilder.conjunction();
        }

        Join<Student, Course> courseJoin = root.join("course", JoinType.LEFT);
        Join<Student, Group> groupJoin = root.join("nameGroup", JoinType.LEFT);

        String likePattern = "%" + keyword.toLowerCase() + "%";
        List<Predicate> predicates = new ArrayList<>();

        for (Field field : Student.class.getDeclaredFields()) {
            if (field.getType().equals(String.class)) {
                try {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(field.getName())), likePattern));
                } catch (Exception e) {
                    log.warn("Field '{}' is not accessible via Criteria API", field.getName());
                }
            }
        }

        if (hasAttribute(courseJoin, "courseName")) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(courseJoin.get("courseName")), likePattern
            ));
        }

        if (hasAttribute(groupJoin, "groupName")) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(groupJoin.get("groupName")), likePattern
            ));
        }

        return predicates.isEmpty() ? criteriaBuilder.disjunction() : criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }

    private boolean hasAttribute(Join<?, ?> join, String field) {
        try {
            join.get(field);
            return true;
        } catch (Exception e) {
            log.warn("Field '{}.{}' is not accessible via Criteria API", join, field);
            return false;
        }
    }
}

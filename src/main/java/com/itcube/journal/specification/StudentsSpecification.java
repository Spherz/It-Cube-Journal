package com.itcube.journal.specification;

import com.itcube.journal.model.Course;
import com.itcube.journal.model.Groups;
import com.itcube.journal.model.Staff;
import com.itcube.journal.model.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class StudentsSpecification {

    public Specification<Students> searchByKeyword(String keyword) {
        return new Specification<Students>() {
            @Override
            public Predicate toPredicate(Root<Students> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return buildPredicate(root, criteriaBuilder, keyword);
            }
        };
    }

    private Predicate buildPredicate(Root<Students> root, CriteriaBuilder criteriaBuilder, String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return criteriaBuilder.conjunction();
        }

        Join<Students, Course> courseJoin = root.join("course", JoinType.LEFT);
        Join<Students, Groups> groupJoin = root.join("nameGroup", JoinType.LEFT);

        String likePattern = "%" + keyword.toLowerCase() + "%";
        List<Predicate> predicates = new ArrayList<>();

        for (Field field : Students.class.getDeclaredFields()) {
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

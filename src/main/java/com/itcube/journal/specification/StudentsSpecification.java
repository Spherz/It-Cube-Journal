package com.itcube.journal.specification;

import com.itcube.journal.model.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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

        String likePattern = "%" + keyword + "%";
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

        if (hasAttribute(root, "nameGroup", "groupName")) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("nameGroup").get("groupName")), likePattern
            ));
        }

        if (hasAttribute(root, "staff", "surname")) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("staff").get("surname")), likePattern
            ));
        }

        return predicates.isEmpty() ? criteriaBuilder.disjunction() : criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }

    private boolean hasAttribute(Root<?> root, String entity, String field) {
        try {
            root.get(entity).get(field);
            return true;
        } catch (Exception e) {
            log.warn("Field '{}.{}' is not accessible via Criteria API", entity, field);
            return false;
        }
    }
}

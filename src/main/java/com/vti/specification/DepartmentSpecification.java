package com.vti.specification;

import com.vti.entity.Department;
import com.vti.form.DepartmentFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DepartmentSpecification {
    public static Specification<Department> buildSpec(DepartmentFilterForm form) {
        if (form == null) {
            return null;
        }
        return (root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(form.getSearch())) {
                String pattern = "%" + form.getSearch().trim() + "%";
                predicates.add(
                        builder.like(root.get("name"), pattern)
                );


            }
            if (form.getMinTotalMembers() != null) {
                predicates.add(builder.greaterThanOrEqualTo(
                        root.get("totalMembers"),
                        form.getMinTotalMembers()
                ));
            }
            if (form.getMaxTotalMembers() != null) {
                predicates.add(builder.lessThanOrEqualTo(
                        root.get("totalMembers"),
                        form.getMaxTotalMembers()
                ));
            }
            if (form.getMinCreatedAt() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("createdAt"), form.getMinCreatedAt()));
            }
            if (form.getMaxCreatedAt() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("createdAt"), form.getMaxCreatedAt()));
            }
            if (form.getMinCreatedYear() != null) {
                predicates.add(builder.greaterThanOrEqualTo(
                        builder.function("YEAR", Integer.class, root.get("createdAt")),
                        form.getMinCreatedYear()));
            }
            if (form.getType() != null) {
                predicates.add(builder.equal(root.get("type"), form.getType()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

package com.tiep.demoapus.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class GenericSpecification {

    public static <T> Specification<T> searchByCodeOrName(String search) {
        return (root, query, builder) -> {
            if (search == null || search.trim().isEmpty()) {
                return builder.conjunction();
            }
            String pattern = "%" + search.trim().toLowerCase() + "%";
            Predicate codePredicate = builder.like(builder.lower(root.get("code")), pattern);
            Predicate namePredicate = builder.like(builder.lower(root.get("name")), pattern);
            return builder.or(codePredicate, namePredicate);
        };
    }
    public static <T> Specification<T> searchByName(String search) {
        return (root, query, builder) -> {
            if (search == null || search.trim().isEmpty()) {
                return builder.conjunction();
            }
            String pattern = "%" + search.trim().toLowerCase() + "%";
            return builder.like(builder.lower(root.get("name")), pattern);
        };
    }
}

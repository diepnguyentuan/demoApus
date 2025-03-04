package com.tiep.demoapus.specification;

import com.tiep.demoapus.entity.JobPositionEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class JobPositionSpecification {
    public static Specification<JobPositionEntity> searchByCodeOrName(String search) {
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
}

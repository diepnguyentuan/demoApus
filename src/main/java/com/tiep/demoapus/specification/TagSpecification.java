package com.tiep.demoapus.specification;

import com.tiep.demoapus.entity.TagEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class TagSpecification {
    public static Specification<TagEntity> searchByName(String search) {
        return (root, query, builder) -> {
            if (search == null || search.trim().isEmpty()) {
                return builder.conjunction();
            }
            String pattern = "%" + search.trim().toLowerCase() + "%";
            return builder.like(builder.lower(root.get("name")), pattern);
        };
    }
}

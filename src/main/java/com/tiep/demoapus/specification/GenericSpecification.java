package com.tiep.demoapus.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

/**
 * Lớp GenericSpecification cung cấp các phương thức tạo điều kiện tìm kiếm chung cho các entity.
 */
public class GenericSpecification {

    public static <T> Specification<T> searchByCodeOrName(String search) {
        return (root, query, builder) -> {
            // Nếu search rỗng hoặc chỉ chứa khoảng trắng, trả về điều kiện luôn đúng
            if (search == null || search.trim().isEmpty()) {
                return builder.conjunction();
            }
            // Tạo chuỗi pattern tìm kiếm dạng "%search%"
            String pattern = "%" + search.trim().toLowerCase() + "%";
            // So sánh trường 'code' với pattern (đã chuyển về chữ thường)
            Predicate codePredicate = builder.like(builder.lower(root.get("code")), pattern);
            // So sánh trường 'name' với pattern (đã chuyển về chữ thường)
            Predicate namePredicate = builder.like(builder.lower(root.get("name")), pattern);
            // Trả về điều kiện OR giữa codePredicate và namePredicate
            return builder.or(codePredicate, namePredicate);
        };
    }

    public static <T> Specification<T> searchByName(String search) {
        return (root, query, builder) -> {
            // Nếu search rỗng hoặc chỉ chứa khoảng trắng, trả về điều kiện luôn đúng
            if (search == null || search.trim().isEmpty()) {
                return builder.conjunction();
            }
            // Tạo chuỗi pattern tìm kiếm dạng "%search%"
            String pattern = "%" + search.trim().toLowerCase() + "%";
            // Trả về điều kiện so sánh trường 'name' với pattern
            return builder.like(builder.lower(root.get("name")), pattern);
        };
    }
}

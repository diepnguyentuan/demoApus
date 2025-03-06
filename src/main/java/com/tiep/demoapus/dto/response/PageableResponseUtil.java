package com.tiep.demoapus.dto.response;

import com.tiep.demoapus.dto.response.PageableResponse;
import org.springframework.data.domain.Page;

public class PageableResponseUtil {
    public static <T> PageableResponse<T> fromPage(Page<T> page, String sort) {
        return PageableResponse.<T>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .sort(sort)
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .numberOfElements(page.getNumberOfElements())
                .build();
    }
}

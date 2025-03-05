package com.tiep.demoapus.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@Builder
public class PageableResponse<T> {
    @Builder.Default
    private List<T> content = Collections.emptyList();
    private int page;
    private int size;
    private String sort;
    private int totalPages;
    private long totalElements;
    private int numberOfElements;
}

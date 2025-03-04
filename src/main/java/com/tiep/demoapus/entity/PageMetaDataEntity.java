package com.tiep.demoapus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageMetaDataEntity {
    private int page;
    private int size;
    private String sort;
    private long totalElements;
    private int totalPages;
    private int numberOfElements;
}

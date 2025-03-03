package com.tiep.demoapus.entity;

public class PageMetaData {
    private int page;
    private int size;
    private String sort;
    private long totalElements;
    private int totalPages;
    private int numberOfElements;

    public PageMetaData(int page, int size, String sort, long totalElements, int totalPages, int numberOfElements) {
        this.page = page;
        this.size = size;
        this.sort = sort;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.numberOfElements = numberOfElements;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getSort() {
        return sort;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }
}

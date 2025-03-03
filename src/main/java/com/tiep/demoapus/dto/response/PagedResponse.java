package com.tiep.demoapus.dto.response;

import com.tiep.demoapus.entity.PageMetaData;

import java.util.List;

public class PagedResponse<T> {
    private List<T> content;
    private PageMetaData metadata;

    public PagedResponse(List<T> content, PageMetaData metadata) {
        this.content = content;
        this.metadata = metadata;
    }

    public List<T> getContent() {
        return content;
    }

    public PageMetaData getMetadata() {
        return metadata;
    }
}

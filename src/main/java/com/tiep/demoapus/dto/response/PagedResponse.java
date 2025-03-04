package com.tiep.demoapus.dto.response;

import com.tiep.demoapus.entity.PageMetaDataEntity;

import java.util.List;

public class PagedResponse<T> {
    private List<T> content;
    private PageMetaDataEntity metadata;

    public PagedResponse(List<T> content, PageMetaDataEntity metadata) {
        this.content = content;
        this.metadata = metadata;
    }

    public List<T> getContent() {
        return content;
    }

    public PageMetaDataEntity getMetadata() {
        return metadata;
    }
}

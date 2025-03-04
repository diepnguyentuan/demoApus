package com.tiep.demoapus.service;

import com.tiep.demoapus.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAllTags();
    Tag getTagById(Long id);
    Tag addTag(Tag tag);
    Tag updateTag(Tag tag);
    void deleteTag(Long id);
    boolean existsById(Long id);
}

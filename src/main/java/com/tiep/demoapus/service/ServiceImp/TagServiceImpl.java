package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.entity.Tag;
import com.tiep.demoapus.repository.TagRepository;
import com.tiep.demoapus.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepository tagRepository;

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTagById(Long id) {
        return tagRepository.findById(id).orElse(null);
    }

    @Override
    public Tag addTag(Tag tag) {
        LocalDateTime now = LocalDateTime.now();
        if(tag.getCreated_at() == null) {
            tag.setCreated_at(now);
        }
        tag.setUpdated_at(now);
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(Tag tag) {
        tag.setUpdated_at(LocalDateTime.now());
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Long id) {
        Tag tag = getTagById(id);
        if (tag != null) {
            tagRepository.delete(tag);
        }
    }

    @Override
    public boolean existsById(Long id) {
        return tagRepository.existsById(id);
    }

}

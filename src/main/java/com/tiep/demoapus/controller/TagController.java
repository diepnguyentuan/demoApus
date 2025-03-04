package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.TagRequestDTO;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.dto.response.TagResponseDTO;
import com.tiep.demoapus.entity.Industry;
import com.tiep.demoapus.entity.Tag;
import com.tiep.demoapus.mapper.IndustryMapper;
import com.tiep.demoapus.mapper.TagMapper;
import com.tiep.demoapus.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        List<Tag> tags = tagService.getAllTags();
        List<TagResponseDTO> tagDTOs = new ArrayList<>();
        for (Tag tag : tags) {
            tagDTOs.add(TagMapper.toDTO(tag));
        }
        return ResponseEntity.ok(tagDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Tag tag = tagService.getTagById(id);
        if (tag == null) {
            return ResponseEntity.badRequest().body("tag not found");
        }
        return ResponseEntity.ok(TagMapper.toDTO(tag));
    }

    @PostMapping("")
    public ResponseEntity<?> addTag(@RequestBody TagRequestDTO tagDTO) {
        Tag tag = TagMapper.toEntity(tagDTO);
        Tag savedTag = tagService.addTag(tag);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", savedTag.getId())));
    }

    @PutMapping("")
    public ResponseEntity<?> updateTag(@RequestBody TagRequestDTO tagDTO) {
        Tag tag = TagMapper.toEntity(tagDTO);
        Tag savedTag = tagService.updateTag(tag);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", savedTag.getId())));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTag(@PathVariable Long id) {
        if (!tagService.existsById(id)) {
            return ResponseEntity.badRequest().body("tag not found");
        }
        tagService.deleteTag(id);
        return ResponseEntity.ok("tag deleted successfully");
    }

}

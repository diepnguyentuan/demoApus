package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.TagRequestDTO;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.dto.response.TagResponseDTO;
import com.tiep.demoapus.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<?> getAllTags(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search) {
        Page<TagResponseDTO> pageData = tagService.getAllTags(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(pageData));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTagById(@PathVariable Long id) {
        TagResponseDTO dto = tagService.getTagById(id);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("data", dto)));
    }

    @PostMapping
    public ResponseEntity<?> addTag(@RequestBody TagRequestDTO tagRequestDTO) {
        TagResponseDTO dto = tagService.addTag(tagRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", dto.getId())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTag(@PathVariable Long id, @RequestBody TagRequestDTO tagRequestDTO) {
        TagResponseDTO dto = tagService.updateTag(id, tagRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", dto.getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable Long id) {
        if (!tagService.existsById(id)) {
            return ResponseEntity.badRequest().body(new ResponseWrapper("Tag not found"));
        }
        tagService.deleteTag(id);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("message", "Tag deleted successfully")));
    }
}

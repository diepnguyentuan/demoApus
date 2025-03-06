package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.TagRequestDTO;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.dto.response.TagResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllTags(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search) {
        PageableResponse<TagResponseDTO> data = tagService.getAllTags(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTagById(@PathVariable Long id) {
        TagResponseDTO dto = tagService.getTagById(id);
        return ResponseEntity.ok(new ResponseWrapper(dto));
    }

    @PostMapping("")
    public ResponseEntity<?> addTag(@RequestBody TagRequestDTO tagRequestDTO) {
        TagResponseDTO dto = tagService.addTag(tagRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", dto.getId())));
    }

    @PutMapping
    public ResponseEntity<?> updateTag(@RequestBody TagRequestDTO tagRequestDTO) {
        TagResponseDTO dto = tagService.updateTag(tagRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper((Map.of("id", dto.getId()))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok("Deleted Tag");
    }
}

package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.SourceRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.dto.response.SourceResponseDTO;
import com.tiep.demoapus.service.SourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/source")
@RequiredArgsConstructor
public class SourceController {

    private final SourceService sourceService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllSources(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search
    ) {
        PageableResponse<SourceResponseDTO> data = sourceService.findAllSource(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSourceById(@PathVariable Long id) {
        SourceResponseDTO source = sourceService.findSourceById(id);
        return ResponseEntity.ok(new ResponseWrapper(source));
    }

    @PostMapping("")
    public ResponseEntity<?> addSource(@RequestBody SourceRequestDTO source) {
        SourceResponseDTO dto = sourceService.addSource(source);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", dto.getId())));
    }

    @PutMapping("")
    public ResponseEntity<?> updateSource(@RequestBody SourceRequestDTO source) {
        SourceResponseDTO dto = sourceService.updateSource(source);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", dto.getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSource(@PathVariable Long id) {
        sourceService.deleteSourceById(id);
        return ResponseEntity.ok("Deleted successfully");
    }


}

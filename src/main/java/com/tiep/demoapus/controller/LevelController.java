package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.LevelRequestDTO;
import com.tiep.demoapus.dto.response.LevelResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/level")
@RequiredArgsConstructor
public class LevelController {
    private final LevelService levelService;

    @GetMapping("/list")
    public ResponseEntity<?> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search
    ) {
        PageableResponse<LevelResponseDTO> data = levelService.getAllLevels(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        LevelResponseDTO level = levelService.getLevelById(id);
        return ResponseEntity.ok(new ResponseWrapper(level));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody LevelRequestDTO level) {
        LevelResponseDTO data = levelService.addLevel(level);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", data.getId())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody LevelRequestDTO level) {
        LevelResponseDTO data = levelService.updateLevel(level);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", data.getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        levelService.deleteLevelById(id);
        return ResponseEntity.ok("Deleted");
    }
}

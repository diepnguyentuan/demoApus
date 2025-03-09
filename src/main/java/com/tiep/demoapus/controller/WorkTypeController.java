package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.WorkTypeRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.dto.response.WorkTypeResponseDTO;
import com.tiep.demoapus.service.WorkTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/work-type")
@RequiredArgsConstructor
public class WorkTypeController {
    private final WorkTypeService workTypeService;

    @GetMapping("/list")
    public ResponseEntity<?> getWorkTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search
    ) {
        PageableResponse<WorkTypeResponseDTO> data = workTypeService.getAllWorkTypes(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkType(@PathVariable Long id) {
        WorkTypeResponseDTO workType = workTypeService.getWorkTypeById(id);
        return ResponseEntity.ok(new ResponseWrapper(workType));
    }

    @PostMapping
    public ResponseEntity<?> createWorkType(@RequestBody WorkTypeRequestDTO workTypeRequestDTO) {
        WorkTypeResponseDTO data = workTypeService.addWorkType(workTypeRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", data.getId())));
    }

    @PutMapping
    public ResponseEntity<?> updateWorkType(@RequestBody WorkTypeRequestDTO workTypeRequestDTO) {
        WorkTypeResponseDTO data = workTypeService.addWorkType(workTypeRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", data.getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkType(@PathVariable Long id) {
        workTypeService.deleteWorkTypeById(id);
        return ResponseEntity.ok("Deleted work type with id " + id);
    }
}

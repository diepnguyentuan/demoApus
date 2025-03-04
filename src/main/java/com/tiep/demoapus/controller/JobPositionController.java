package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.JobPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/job-positions")
@RequiredArgsConstructor
public class JobPositionController {

    private final JobPositionService jobPositionService;

    @PostMapping
    public ResponseEntity<?> addJobPosition(@RequestBody JobPositionRequestDTO requestDTO) {
        JobPositionResponseDTO responseDTO = jobPositionService.addJobPosition(requestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", responseDTO.getId())));
    }

    @GetMapping
    public ResponseEntity<?> getAllJobPositions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search) {
        Page<JobPositionResponseDTO> pageData = jobPositionService.getAllJobPositions(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(pageData));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJobPosition(@PathVariable Long id, @RequestBody JobPositionRequestDTO requestDTO) {
        JobPositionResponseDTO responseDTO = jobPositionService.updateJobPosition(id, requestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", responseDTO.getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJobPosition(@PathVariable Long id) {
        jobPositionService.deleteJobPosition(id);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("message", "Deleted successfully")));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobPositionById(@PathVariable Long id) {
        JobPositionResponseDTO responseDTO = jobPositionService.getJobPositionById(id);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("data", responseDTO)));
    }
}

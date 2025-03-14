package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.JobPositionMapResponseDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.service.JobPositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/job-positions")
@RequiredArgsConstructor
public class JobPositionController {

    private final JobPositionService jobPositionService;

    @PostMapping
    public ResponseEntity<Map<String, Long>> addJobPosition(@Valid @RequestBody JobPositionRequestDTO requestDTO) {
        JobPositionResponseDTO responseDTO = jobPositionService.addJobPosition(requestDTO);
        return ResponseEntity.ok(Map.of("id", responseDTO.getId()));
    }

    @GetMapping("/list")
    public ResponseEntity<PageableResponse<JobPositionResponseDTO>> getAllJobPositions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search) {

        PageableResponse<JobPositionResponseDTO> data = jobPositionService.getAllJobPositions(page, size, sort, search);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPositionMapResponseDTO> getJobPositionById(@PathVariable Long id) {
        JobPositionMapResponseDTO responseDTO = jobPositionService.getJobPositionById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping
    public ResponseEntity<Map<String, Long>> updateJobPosition(@Valid @RequestBody JobPositionRequestDTO requestDTO) {
        JobPositionResponseDTO responseDTO = jobPositionService.updateJobPosition(requestDTO);
        return ResponseEntity.ok(Map.of("id", responseDTO.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPosition(@PathVariable Long id) {
        jobPositionService.deleteJobPosition(id);
        return ResponseEntity.noContent().build();
    }
}

package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.JobPositionService;
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
    public ResponseEntity<?> addJobPosition(@RequestBody JobPositionRequestDTO requestDTO) {
        JobPositionResponseDTO responseDTO = jobPositionService.addJobPosition(requestDTO);
        return ResponseEntity.ok(new ResponseWrapper((Map.of("id", responseDTO.getId()))));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllJobPositions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search) {
        PageableResponse<JobPositionResponseDTO> data = jobPositionService.getAllJobPositions(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    @PutMapping
    public ResponseEntity<?> updateJobPosition(@RequestBody JobPositionRequestDTO requestDTO) {
        JobPositionResponseDTO responseDTO = jobPositionService.updateJobPosition(requestDTO.getId(), requestDTO);
        return ResponseEntity.ok(new ResponseWrapper((Map.of("id", responseDTO.getId()))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJobPosition(@PathVariable Long id) {
        jobPositionService.deleteJobPosition(id);
        return ResponseEntity.ok(new ResponseWrapper("Deleted successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobPositionById(@PathVariable Long id) {
        JobPositionResponseDTO responseDTO = jobPositionService.getJobPositionById(id);
        return ResponseEntity.ok(new ResponseWrapper(responseDTO));
    }
}

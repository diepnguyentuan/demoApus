package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.JobPositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/job-positions")
public class JobPositionController {
    private final JobPositionService jobPositionService;

    public JobPositionController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    @PostMapping("")
    public ResponseEntity<?> addJobPosition(@RequestBody JobPositionRequestDTO requestDTO) {
        JobPositionResponseDTO responseDTO = jobPositionService.addJobPosition(requestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", responseDTO.getId())));
    }

    @GetMapping("/list")
    public ResponseEntity<List<JobPositionResponseDTO>> getAll() {
        return ResponseEntity.ok(jobPositionService.getAllJobPositions());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobPositionResponseDTO> updateJobPosition(@PathVariable Long id, @RequestBody JobPositionRequestDTO requestDTO) {
        return ResponseEntity.ok(jobPositionService.updateJobPosition(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPosition(@PathVariable Long id) {
        jobPositionService.deleteJobPosition(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPositionResponseDTO> getJobPositionById(@PathVariable Long id) {
        return ResponseEntity.ok(jobPositionService.getJobPositionById(id));
    }
}

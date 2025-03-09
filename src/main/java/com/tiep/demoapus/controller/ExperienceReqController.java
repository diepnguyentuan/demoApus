package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.ExperienceRequirementRequestDTO;
import com.tiep.demoapus.dto.response.ExperienceRequirementResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.ExperienceRequirementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/experience-req")
@RequiredArgsConstructor
public class ExperienceReqController {
    private final ExperienceRequirementService experienceReqService;

    @GetMapping("/list")
    public ResponseEntity<?> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search
    ) {
        PageableResponse<ExperienceRequirementResponseDTO> data = experienceReqService.getAllExRequirement(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ExperienceRequirementResponseDTO data = experienceReqService.geExperienceRequirementResponseDto(id);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ExperienceRequirementRequestDTO requestDTO) {
        ExperienceRequirementResponseDTO data = experienceReqService.addExperienceRequirementResponseDto(requestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", data.getId())));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ExperienceRequirementRequestDTO requestDTO) {
        ExperienceRequirementResponseDTO data = experienceReqService.addExperienceRequirementResponseDto(requestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", data.getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        experienceReqService.deleteExRequirement(id);
        return ResponseEntity.ok("Experience Requirement Deleted");
    }
}

package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.ReasonHiringRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ReasonHiringResponseDTO;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.ReasonHiringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/reason-hiring")
@RequiredArgsConstructor
public class ReasonHiringController {
    private final ReasonHiringService reasonHiringService;

    @GetMapping("/list")
    public ResponseEntity<?> getReasonHiring(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search
    ) {
        PageableResponse<ReasonHiringResponseDTO> data = reasonHiringService.getAllReasonHirings(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }
    @PostMapping
    public ResponseEntity<?> addReasonHiring(@RequestBody ReasonHiringRequestDTO reasonHiringRequestDTO) {
        ReasonHiringResponseDTO data = reasonHiringService.addReasonHiring(reasonHiringRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", data.getId())));
    }
}

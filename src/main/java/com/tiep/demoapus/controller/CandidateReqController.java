package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.CandidateReqRequestDTO;
import com.tiep.demoapus.dto.response.CandidateReqResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.CandidateReqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/candidate-req")
@RequiredArgsConstructor
public class CandidateReqController {
    private final CandidateReqService candidateReqService;

    @GetMapping("/list")
    public ResponseEntity<?> listCandidateReq(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search
    ) {
        PageableResponse<CandidateReqResponseDTO> data = candidateReqService.getAllCandidateReq(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCandidateReqById(@PathVariable Long id) {
        CandidateReqResponseDTO data = candidateReqService.getCandidateReqById(id);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    @PostMapping
    public ResponseEntity<?> addCandidateReq(@RequestBody CandidateReqRequestDTO candidateReqRequestDTO) {
        CandidateReqResponseDTO data = candidateReqService.addCandidateReq(candidateReqRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", data.getId())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCandidateReq(@RequestBody CandidateReqRequestDTO candidateReqRequestDTO) {
        CandidateReqResponseDTO data = candidateReqService.updateCandidateReq(candidateReqRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", data.getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCandidateReq(@PathVariable Long id) {
        candidateReqService.deleteCandidateReq(id);
        return ResponseEntity.ok("Deleted CandidateReq");
    }
}

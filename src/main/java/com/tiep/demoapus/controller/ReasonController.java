package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.ReasonRequestDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ReasonResponseDTO;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.ReasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reason")
@RequiredArgsConstructor
public class ReasonController {
    private final ReasonService reasonService;

    @PostMapping
    public ResponseEntity<?> addReason(@RequestBody ReasonRequestDTO requestDTO) {
        ReasonResponseDTO dto = reasonService.addReason(requestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", dto.getId())));
    }

    @PutMapping
    public ResponseEntity<?> updateReason(@RequestBody ReasonRequestDTO requestDTO) {
        ReasonResponseDTO dto = reasonService.updateReason(requestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", dto.getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReason(@PathVariable Long id) {
        reasonService.deleteReason(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @GetMapping("/list")
    public ResponseEntity<?> listReasons(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search) {
        {
            PageableResponse<ReasonResponseDTO> data = reasonService.getAllReasons(page, size, sort, search);
            return ResponseEntity.ok(new ResponseWrapper(data));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReasonById(@PathVariable Long id) {
        ReasonResponseDTO dto = reasonService.getReasonById(id);
        return ResponseEntity.ok(new ResponseWrapper(dto));
    }

}

package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.ReasonRequestDTO;
import com.tiep.demoapus.dto.response.ReasonResponseDTO;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.ReasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

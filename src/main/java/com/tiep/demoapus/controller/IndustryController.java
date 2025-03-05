package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.IndustryRequestDTO;
import com.tiep.demoapus.dto.response.IndustryResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.IIndustryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/industries")
@RequiredArgsConstructor
public class IndustryController {

    private final IIndustryService industryService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllIndustries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search) {
        PageableResponse<IndustryResponseDTO> data = industryService.getAllIndustries(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIndustry(@PathVariable Long id) {
        IndustryResponseDTO dto = industryService.getIndustryById(id);
        return ResponseEntity.ok(new ResponseWrapper(dto));
    }

    @PostMapping
    public ResponseEntity<?> addIndustry(@RequestBody IndustryRequestDTO requestDTO) {
        IndustryResponseDTO dto = industryService.addIndustry(requestDTO);
        return ResponseEntity.ok(new ResponseWrapper((Map.of("id", dto.getId()))));
    }

    @PutMapping
    public ResponseEntity<?> updateIndustry(@RequestBody IndustryRequestDTO industryRequestDTO) {
        IndustryResponseDTO dto = industryService.updateIndustry(industryRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper((Map.of("id", dto.getId()))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIndustry(@PathVariable Long id) {
        if (!industryService.existsById(id)) {
            return ResponseEntity.badRequest().body(new ResponseWrapper("Industry not found"));
        }
        industryService.deleteIndustry(id);
        return ResponseEntity.ok(new ResponseWrapper("Industry deleted successfully"));
    }
}

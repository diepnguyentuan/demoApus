package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.IndustryRequestDTO;
import com.tiep.demoapus.dto.response.IndustryResponseDTO;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.IIndustryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/industries")
@RequiredArgsConstructor
public class IndustryController {

    private final IIndustryService industryService;

    @GetMapping
    public ResponseEntity<?> getIndustries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search) {
        Page<IndustryResponseDTO> pageData = industryService.getIndustries(page, size, sort, search);
        // Định dạng response theo mẫu: { data: { content: [...], page, size, sort, totalElements, totalPages, numberOfElements } }
        return ResponseEntity.ok(Collections.singletonMap("data", pageData));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIndustry(@PathVariable Long id) {
        IndustryResponseDTO dto = industryService.getIndustryById(id);
        return ResponseEntity.ok(Collections.singletonMap("data", dto));
    }

    @PostMapping
    public ResponseEntity<?> addIndustry(@RequestBody IndustryRequestDTO requestDTO) {
        IndustryResponseDTO dto = industryService.addIndustry(requestDTO);
        return ResponseEntity.ok(Collections.singletonMap("data", dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateIndustry(@PathVariable Long id, @RequestBody IndustryRequestDTO requestDTO) {
        IndustryResponseDTO dto = industryService.updateIndustry(id, requestDTO);
        return ResponseEntity.ok(Collections.singletonMap("data", dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIndustry(@PathVariable Long id) {
        industryService.deleteIndustry(id);
        Map<String, Object> response = Collections.singletonMap("data", "Deleted successfully");
        return ResponseEntity.ok(response);
    }
}

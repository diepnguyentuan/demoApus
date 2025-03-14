package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.BenefitRequestDTO;
import com.tiep.demoapus.dto.response.BenefitResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.BenefitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("api/v1/benefits")
@RequiredArgsConstructor
public class BenefitController {
    private final BenefitService benefitService;
    private final MessageSource messageSource;

    @GetMapping("/{id}/localized")
    public ResponseEntity<Map<String, Object>> getLocalizedBenefit(@PathVariable Long id, Locale locale) {
        BenefitResponseDTO benefit = benefitService.getBenefitById(id);
        String localizedMessage = messageSource.getMessage("benefit.greeting", new Object[]{benefit.getName()}, locale);
        Map<String, Object> response = new HashMap<>();
        response.put("benefit", benefit);
        response.put("message", localizedMessage);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listBenefits(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search
    ) {
        PageableResponse<BenefitResponseDTO> data = benefitService.getBenefits(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBenefitById(@PathVariable Long id) {
        BenefitResponseDTO data = benefitService.getBenefitById(id);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    @PostMapping
    public ResponseEntity<?> addBenefit(@RequestBody BenefitRequestDTO benefitRequestDTO) {
        BenefitResponseDTO data = benefitService.addBenefit(benefitRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", data.getId())));
    }

    @PutMapping
    public ResponseEntity<?> updateBenefit(@PathVariable Long id, @RequestBody BenefitRequestDTO benefitRequestDTO) {
        benefitRequestDTO.setId(id); // Đảm bảo ID khớp với đường dẫn
        BenefitResponseDTO data = benefitService.updateBenefit(benefitRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", data.getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBenefit(@PathVariable Long id) {
        benefitService.deleteBenefit(id);
        return ResponseEntity.ok(new ResponseWrapper("Deleted Benefit"));
    }
}

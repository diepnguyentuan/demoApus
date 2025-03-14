package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.BenefitRequestDTO;
import com.tiep.demoapus.dto.response.BenefitResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.service.BenefitService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/benefits")
@RequiredArgsConstructor
public class BenefitController {
    private final BenefitService benefitService;
    private final MessageSource messageSource;

    @GetMapping("/{id}")
    public ResponseEntity<?> getBenefitById(
            @PathVariable Long id,
            @RequestHeader(value = "Accept-Language", required = false) String language) {

        BenefitResponseDTO benefit = benefitService.getBenefitById(id);
        if (language != null) {
            Locale locale = Locale.forLanguageTag(language);
            String localizedMessage = messageSource.getMessage("benefit.greeting", new Object[]{benefit.getName()}, locale);
            return ResponseEntity.ok(Map.of("benefit", benefit, "message", localizedMessage));
        }
        return ResponseEntity.ok(benefit);
    }

    @GetMapping("/list")
    public ResponseEntity<PageableResponse<BenefitResponseDTO>> listBenefits(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search) {

        PageableResponse<BenefitResponseDTO> data = benefitService.getBenefits(page, size, sort, search);
        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<Map<String, Long>> addBenefit(@Valid @RequestBody BenefitRequestDTO benefitRequestDTO) {
        BenefitResponseDTO data = benefitService.addBenefit(benefitRequestDTO);
        return ResponseEntity.ok(Map.of("id", data.getId()));
    }

    @PutMapping
    public ResponseEntity<Map<String, Long>> updateBenefit(@Valid @RequestBody BenefitRequestDTO benefitRequestDTO) {
        BenefitResponseDTO data = benefitService.updateBenefit(benefitRequestDTO);
        return ResponseEntity.ok(Map.of("id", data.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBenefit(@PathVariable Long id) {
        benefitService.deleteBenefit(id);
        return ResponseEntity.ok("Deleted Benefit");
    }
}

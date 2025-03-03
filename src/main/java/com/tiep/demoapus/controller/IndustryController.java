package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.IndustryRequestDTO;
import com.tiep.demoapus.dto.response.IndustryResponseDTO;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.mapper.IndustryMapper;
import com.tiep.demoapus.entity.Industry;
import com.tiep.demoapus.service.IIndustryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller quản lý các API liên quan đến Industry.
 * Bao gồm các API: lấy danh sách, lấy theo id, thêm mới, cập nhật và xóa.
 */
@RestController
@RequestMapping("api/v1/industry")
@Tag(name = "Quản lý ngành nghề")
public class IndustryController {

    @Autowired
    private IIndustryService industryService;

    @Operation(summary = "Lấy danh sách ngành nghề", description = "Trả về danh sách tất cả các ngành nghề có phân trang")
    @GetMapping("/list")
    public ResponseEntity<?> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort) {

        // Lấy danh sách Industry từ service có phân trang
        Page<Industry> industries = industryService.getIndustries(page, size, sort);

        // Chuyển đổi danh sách Industry thành DTO
        List<IndustryResponseDTO> content = IndustryMapper.toDTOList(industries.getContent());

        // Đóng gói dữ liệu theo format mong muốn
        Map<String, Object> response = new HashMap<>();
        response.put("content", content);
        response.put("page", industries.getNumber());
        response.put("size", industries.getSize());
        response.put("sort", sort);
        response.put("totalElements", industries.getTotalElements());
        response.put("totalPages", industries.getTotalPages());
        response.put("numberOfElements", industries.getNumberOfElements());

        return ResponseEntity.ok(new ResponseWrapper(response));
    }


    @Operation(summary = "Lấy ngành nghề theo ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Industry industry = industryService.getIndustryById(id);
        if (industry == null) {
            return ResponseEntity.badRequest().body("Industry not found");
        }
        return ResponseEntity.ok(IndustryMapper.toDTO(industry));
    }

    @Operation(summary = "Thêm ngành nghề mới")
    @PostMapping("")
    public ResponseEntity<?> addIndustry(@RequestBody IndustryRequestDTO industryRequestDTO) {
        if (industryService.existsByCode(industryRequestDTO.getCode())) {
            return ResponseEntity.badRequest().body("Industry code already exists");
        }
        Industry industry = IndustryMapper.toEntity(industryRequestDTO);
        Industry newIndustry = industryService.addIndustry(industry);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", newIndustry.getId())));
    }

    @Operation(summary = "Cập nhật ngành nghề")
    @PutMapping("")
    public ResponseEntity<?> updateIndustry(@RequestBody IndustryRequestDTO updateDTO) {
        Long id = updateDTO.getId();
        if (id == null) {
            return ResponseEntity.badRequest().body("ID is required for update");
        }
        if (!industryService.existsById(id)) {
            return ResponseEntity.badRequest().body("Industry not found");
        }
        Industry industry = industryService.getIndustryById(id);
        industry.setCode(updateDTO.getCode());
        industry.setName(updateDTO.getName());
        industry.setActive(updateDTO.getIsActive());
        industry.setDescription(updateDTO.getDescription());
        Industry updatedIndustry = industryService.updateIndustry(industry);
        return ResponseEntity.ok(updatedIndustry.getId());
    }

    @Operation(summary = "Xóa ngành nghề")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteIndustry(@PathVariable Long id) {
        if (!industryService.existsById(id)) {
            return ResponseEntity.badRequest().body("Industry not found");
        }
        industryService.deleteIndustry(id);
        return ResponseEntity.ok("Industry deleted successfully");
    }
}

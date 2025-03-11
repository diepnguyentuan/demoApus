package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.JobPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/job-positions")
@RequiredArgsConstructor
public class JobPositionController {

    private final JobPositionService jobPositionService;

    /**
     * Tạo JobPosition mới.
     * Request body chứa code, name, mô tả, v.v. và danh sách department + positions.
     */
    @PostMapping
    public ResponseEntity<?> addJobPosition(@RequestBody JobPositionRequestDTO requestDTO) {
        JobPositionResponseDTO responseDTO = jobPositionService.addJobPosition(requestDTO);
        // Ở đây trả về JSON chỉ có "id"
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", responseDTO.getId())));
    }

    /**
     * Lấy danh sách JobPosition, kèm tên department/position từ API bên ngoài.
     */
    @GetMapping("/list")
    public ResponseEntity<?> getAllJobPositions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search
    ) {
        // Ở ví dụ này ta bỏ bớt phần phân trang/sort cho gọn
        PageableResponse<JobPositionResponseDTO> data = jobPositionService.getAllJobPositions(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    /**
     * Lấy chi tiết 1 JobPosition, kèm tên department/position từ API bên ngoài.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getJobPositionById(@PathVariable Long id) {
        JobPositionResponseDTO responseDTO = jobPositionService.getJobPositionById(id);
        return ResponseEntity.ok(new ResponseWrapper(responseDTO));
    }
}


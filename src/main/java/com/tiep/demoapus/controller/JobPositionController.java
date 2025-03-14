package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.JobPositionMapResponseDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.JobPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        // Trả về JSON chứa chỉ id của job position mới tạo
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
        // Ở ví dụ này, phần phân trang và sort được đơn giản hóa
        PageableResponse<JobPositionResponseDTO> data = jobPositionService.getAllJobPositions(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    /**
     * Lấy chi tiết JobPosition theo id, bao gồm dữ liệu mapping từ bảng job_position_map.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getJobPositionById(@PathVariable Long id) {
        JobPositionMapResponseDTO responseDTO = jobPositionService.getJobPositionById(id);
        return ResponseEntity.ok(new ResponseWrapper(responseDTO));
    }

    /**
     * Cập nhật JobPosition theo id.
     * Id được truyền qua URL và thông tin cập nhật trong request body.
     */
    @PutMapping
    public ResponseEntity<?> updateJobPosition(@RequestBody JobPositionRequestDTO requestDTO) {
        JobPositionResponseDTO responseDTO = jobPositionService.updateJobPosition(requestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", responseDTO.getId())));
    }

    /**
     * Xóa JobPosition theo id.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJobPosition(@PathVariable Long id) {
        jobPositionService.deleteJobPosition(id);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("message", "Job position deleted successfully")));
    }
}

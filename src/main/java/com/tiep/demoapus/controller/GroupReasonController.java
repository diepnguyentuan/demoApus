package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.GroupReasonRequestDTO;
import com.tiep.demoapus.dto.response.GroupReasonResponseDTO;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.GroupReasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/group-reason")
@RequiredArgsConstructor
public class GroupReasonController {

    private final GroupReasonService groupReasonService;

    // Endpoint list trả về Page và được bọc theo định dạng: { data: { content: [...], page, size, sort, totalElements, totalPages, numberOfElements } }
    @GetMapping
    public ResponseEntity<?> getGroupReasons(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search) {
        Page<GroupReasonResponseDTO> pageData = groupReasonService.getAllGroupReasons(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(pageData));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getGroupReason(@PathVariable Long id) {
        GroupReasonResponseDTO dto = groupReasonService.getGroupReasonById(id);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("data", dto)));
    }

    @PostMapping
    public ResponseEntity<?> addGroupReason(@RequestBody GroupReasonRequestDTO requestDTO) {
        GroupReasonResponseDTO dto = groupReasonService.addGroupReason(requestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", dto.getId())));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateGroupReason(@PathVariable Long id, @RequestBody GroupReasonRequestDTO requestDTO) {
        GroupReasonResponseDTO dto = groupReasonService.updateGroupReason(id, requestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", dto.getId())));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteGroupReason(@PathVariable Long id) {
        groupReasonService.deleteGroupReason(id);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("message", "Deleted successfully")));
    }
}

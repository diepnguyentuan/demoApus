package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.GroupReasonRequestDTO;
import com.tiep.demoapus.dto.response.GroupReasonResponseDTO;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.entity.GroupReason;
import com.tiep.demoapus.mapper.GroupReasonMapper;
import com.tiep.demoapus.service.GroupReasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/group-reason")
@RequiredArgsConstructor
public class GroupReasonController {

    private final GroupReasonService groupReasonService;

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        List<GroupReason> groupReasons = groupReasonService.getAllGroupReasons();
        List<GroupReasonResponseDTO> groupReasonDTOS = new ArrayList<>();
        for (GroupReason groupReason : groupReasons) {
            groupReasonDTOS.add(GroupReasonMapper.toDTO(groupReason));
        }
        return ResponseEntity.ok(groupReasonDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getGroupReason(@PathVariable Long id) {
        GroupReason groupReason = groupReasonService.getGroupReasonById(id);
        if (groupReason == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(GroupReasonMapper.toDTO(groupReason));
    }

    @PostMapping("")
    public ResponseEntity<?> addGroupReason(@RequestBody GroupReasonRequestDTO groupReasonRequestDTO) {
        GroupReason groupReason = GroupReasonMapper.toEntity(groupReasonRequestDTO);
        GroupReason savedGroupReason = groupReasonService.addGroupReason(groupReason);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", savedGroupReason.getId())));
    }

    @PutMapping("")
    public ResponseEntity<?> updateGroupReason(@RequestBody GroupReasonRequestDTO groupReasonRequestDTO) {
        GroupReason groupReason = GroupReasonMapper.toEntity(groupReasonRequestDTO);
        GroupReason savedGroupReason = groupReasonService.addGroupReason(groupReason);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", savedGroupReason.getId())));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteGroupReason(@PathVariable Long id) {
        GroupReason groupReason = groupReasonService.getGroupReasonById(id);
        if (groupReason == null) {
            return ResponseEntity.notFound().build();
        }
        groupReasonService.deleteGroupReason(id);
        return ResponseEntity.ok().build();
    }
}

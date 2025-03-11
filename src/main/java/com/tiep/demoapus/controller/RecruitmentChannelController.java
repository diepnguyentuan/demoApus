package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.RecruitmentChannelRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.RecruitmentChannelResponseDTO;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.RecruitmentChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/recruitment_channel")
@RequiredArgsConstructor
public class RecruitmentChannelController {

    private final RecruitmentChannelService recruitmentChannelService;

    @PostMapping
    public ResponseEntity<?> addRecruitmentChannel(@RequestBody RecruitmentChannelRequestDTO requestDTO) {
        RecruitmentChannelResponseDTO responseDTO = recruitmentChannelService.addRecruitmentChannel(requestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", responseDTO.getId())));
    }

    @GetMapping("/list")
    public ResponseEntity<?> listRecruitmentChannels(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search
    ) {
        PageableResponse<RecruitmentChannelResponseDTO> data = recruitmentChannelService.getAllRecruitmentChannels(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecruitmentChannelById(@PathVariable("id") Long id) {
        RecruitmentChannelResponseDTO data = recruitmentChannelService.getRecruitmentChannel(id);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    @PutMapping
    public ResponseEntity<?> updateRecruitmentChannel(@RequestBody RecruitmentChannelRequestDTO requestDTO) {
        RecruitmentChannelResponseDTO responseDTO = recruitmentChannelService.updateRecruitmentChannel(requestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", responseDTO.getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecruitmentChannel(@PathVariable Long id) {
        recruitmentChannelService.deleteRecruitmentChannel(id);
        return ResponseEntity.ok("Deleted recruitment channel");
    }
}

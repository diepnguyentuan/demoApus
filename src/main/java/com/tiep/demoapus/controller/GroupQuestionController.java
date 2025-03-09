package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.GroupQuestionRequestDTO;
import com.tiep.demoapus.dto.response.GroupQuestionResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.GroupQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/group-question")
@RequiredArgsConstructor
public class GroupQuestionController {

    private final GroupQuestionService groupQuestionService;

    @GetMapping("/list")
    public ResponseEntity<?> getGroupQuestions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search
    ) {
        PageableResponse<GroupQuestionResponseDTO> data = groupQuestionService.getAllGroupQuestions(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupQuestionById(@PathVariable Long id) {
        GroupQuestionResponseDTO response = groupQuestionService.getGroupQuestionById(id);
        return ResponseEntity.ok(new ResponseWrapper(response));
    }

    @PostMapping
    public ResponseEntity<?> createGroupQuestion(@RequestBody GroupQuestionRequestDTO groupQuestionRequestDTO) {
        GroupQuestionResponseDTO response = groupQuestionService.createGroupQuestion(groupQuestionRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", response.getId())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGroupQuestion(@PathVariable Long id, @RequestBody GroupQuestionRequestDTO groupQuestionRequestDTO) {
        GroupQuestionResponseDTO response = groupQuestionService.createGroupQuestion(groupQuestionRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", response.getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroupQuestion(@PathVariable Long id) {
        groupQuestionService.deleteGroupQuestion(id);
        return ResponseEntity.ok("Deleted group question");
    }
}

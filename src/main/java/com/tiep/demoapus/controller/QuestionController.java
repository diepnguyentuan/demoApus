package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.QuestionRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.QuestionResponseDTO;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllQuestions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search
    ) {
        PageableResponse<QuestionResponseDTO> questions = questionService.getAllQuestions(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(questions));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id) {
        QuestionResponseDTO question = questionService.getQuestionById(id);
        return ResponseEntity.ok(new ResponseWrapper(question));
    }

    @PostMapping
    public ResponseEntity<?> addQuestion(@RequestBody QuestionRequestDTO questionRequestDTO) {
        QuestionResponseDTO dto = questionService.createQuestion(questionRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", dto.getId())));
    }

    @PutMapping
    public ResponseEntity<?> updateQuestion(@RequestBody QuestionRequestDTO questionRequestDTO) {
        QuestionResponseDTO dto = questionService.updateQuestion(questionRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", dto.getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok("Deleted");
    }
}

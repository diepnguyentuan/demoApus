package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.QuestionRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.QuestionResponseDTO;

public interface QuestionService {
    PageableResponse<QuestionResponseDTO> getAllQuestions(int page, int size, String sort, String search);
    QuestionResponseDTO getQuestionById(Long id);
    QuestionResponseDTO createQuestion(QuestionRequestDTO questionRequestDTO);
    QuestionResponseDTO updateQuestion(QuestionRequestDTO questionRequestDTO);
    void deleteQuestion(Long id);
}

package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.GroupQuestionRequestDTO;
import com.tiep.demoapus.dto.response.GroupQuestionResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;

public interface GroupQuestionService {
    PageableResponse<GroupQuestionResponseDTO> getAllGroupQuestions(int page, int size, String sort, String search);
    GroupQuestionResponseDTO getGroupQuestionById(Long id);
    GroupQuestionResponseDTO createGroupQuestion(GroupQuestionRequestDTO groupQuestionRequestDTO);
    GroupQuestionResponseDTO updateGroupQuestion(GroupQuestionRequestDTO groupQuestionRequestDTO);
    void deleteGroupQuestion(Long id);
}

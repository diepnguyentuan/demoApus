package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.ProfileRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ProfileResponseDTO;

public interface ProfileService {
    PageableResponse<ProfileResponseDTO> getAllProfiles(int page, int size, String sort, String search);
    ProfileResponseDTO getProfileById(long id);
    ProfileResponseDTO addProfile(ProfileRequestDTO dto);
    ProfileResponseDTO updateProfile(ProfileRequestDTO dto);
    void deleteProfileById(long id);
}

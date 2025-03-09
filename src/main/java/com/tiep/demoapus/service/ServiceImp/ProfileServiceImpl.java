package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.ProfileRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.PageableResponseUtil;
import com.tiep.demoapus.dto.response.ProfileResponseDTO;
import com.tiep.demoapus.entity.ProfileEntity;
import com.tiep.demoapus.mapper.ProfileMapper;
import com.tiep.demoapus.repository.ProfileRepository;
import com.tiep.demoapus.service.ProfileService;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    @Override
    public PageableResponse<ProfileResponseDTO> getAllProfiles(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<ProfileEntity> entities = profileRepository.findAll(GenericSpecification.searchByCodeOrName(search), pageRequest);
        Page<ProfileResponseDTO> data = entities.map(profileMapper::toDto);
        return PageableResponseUtil.fromPage(data, sort);
    }

    @Override
    public ProfileResponseDTO getProfileById(long id) {
        ProfileEntity profile = profileRepository.findById(id).orElse(null);

        return profileMapper.toDto(profile);
    }

    @Override
    public ProfileResponseDTO addProfile(ProfileRequestDTO dto) {
        ProfileEntity profile = profileMapper.toEntity(dto);

        profile = profileRepository.save(profile);
        return new ProfileResponseDTO(profile.getId());
    }

    @Override
    public ProfileResponseDTO updateProfile(ProfileRequestDTO dto) {
        if (dto.getId() == null) {
            throw new RuntimeException("Id not found");
        }
        ProfileEntity profile = profileRepository.findById(dto.getId()).orElse(null);
        profile.setName(dto.getName());
        profile.setDescription(dto.getDescription());
        profile.setCode(dto.getCode());
        profile.setActive(dto.getActive());
        profile.setUpdatedAt(LocalDateTime.now());
        profile = profileRepository.save(profile);
        return new ProfileResponseDTO(profile.getId());
    }

    @Override
    public void deleteProfileById(long id) {
        if (!profileRepository.existsById(id)) {
            throw new RuntimeException("Id not found");
        }
        profileRepository.deleteById(id);
    }
}

package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.RecruitmentChannelRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.PageableResponseUtil;
import com.tiep.demoapus.dto.response.RecruitmentChannelResponseDTO;
import com.tiep.demoapus.entity.RecruitmentChannelEntity;
import com.tiep.demoapus.mapper.RecruitmentChannelMapper;
import com.tiep.demoapus.repository.RecruitmentChannelRepository;
import com.tiep.demoapus.repository.SourceRepository;
import com.tiep.demoapus.service.RecruitmentChannelService;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RecruitmentChannelServiceImpl implements RecruitmentChannelService {

    private final RecruitmentChannelRepository recruitmentChannelRepository;
    private final RecruitmentChannelMapper recruitmentChannelMapper;
    private final SourceRepository sourceRepository;

    @Override
    public PageableResponse<RecruitmentChannelResponseDTO> getAllRecruitmentChannels(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<RecruitmentChannelEntity> recruitmentChannelEntities = recruitmentChannelRepository.
                findAll(GenericSpecification.searchByName(search), pageRequest);
        Page<RecruitmentChannelResponseDTO> dto = recruitmentChannelEntities.map(recruitmentChannelMapper::toDTO);
        return PageableResponseUtil.fromPage(dto, sort);
    }

    @Override
    public RecruitmentChannelResponseDTO getRecruitmentChannel(int id) {
        return null;
    }

    @Override
    public RecruitmentChannelResponseDTO addRecruitmentChannel(RecruitmentChannelRequestDTO requestDTO) {
        var sourceEntity = sourceRepository.findById(requestDTO.getSourceId())
                .orElseThrow(() -> new RuntimeException("Recruitment channel not found"));
        RecruitmentChannelEntity recruitmentChannelEntity = recruitmentChannelMapper.toEntity(requestDTO);
        recruitmentChannelEntity.setSource(sourceEntity);
        recruitmentChannelEntity.setCreatedAt(LocalDateTime.now());
        recruitmentChannelEntity.setUpdatedAt(LocalDateTime.now());
        recruitmentChannelEntity = recruitmentChannelRepository.save(recruitmentChannelEntity);

        return new RecruitmentChannelResponseDTO(recruitmentChannelEntity.getId());
    }

    @Override
    public RecruitmentChannelResponseDTO updateRecruitmentChannel(RecruitmentChannelRequestDTO requestDTO) {
        var sourceEntity = sourceRepository.findById(requestDTO.getSourceId())
                .orElseThrow(() -> new RuntimeException("Recruitment channel not found"));
        RecruitmentChannelEntity recruitmentChannelEntity = recruitmentChannelMapper.toEntity(requestDTO);
        recruitmentChannelEntity.setSource(sourceEntity);
        recruitmentChannelEntity.setUpdatedAt(LocalDateTime.now());
        recruitmentChannelEntity = recruitmentChannelRepository.save(recruitmentChannelEntity);

        return recruitmentChannelMapper.toDTO(recruitmentChannelEntity);
    }

    @Override
    public void deleteRecruitmentChannel(Long id) {
        if(!recruitmentChannelRepository.existsById(id)) {
            throw new RuntimeException("Recruitment channel not found");
        }
        recruitmentChannelRepository.deleteById(id);
    }
}

package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.CandidateReqRequestDTO;
import com.tiep.demoapus.dto.response.CandidateReqResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.PageableResponseUtil;
import com.tiep.demoapus.entity.CandidateReqEntity;
import com.tiep.demoapus.mapper.CandidateReqMapper;
import com.tiep.demoapus.repository.CandidateReqRepository;
import com.tiep.demoapus.service.CandidateReqService;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CandidateReqServiceImpl implements CandidateReqService {

    private final CandidateReqRepository candidateReqRepository;
    private final CandidateReqMapper candidateReqMapper;

    @Override
    public PageableResponse<CandidateReqResponseDTO> getAllCandidateReq(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<CandidateReqEntity> entities = candidateReqRepository.findAll(GenericSpecification.searchByName(search), pageRequest);
        Page<CandidateReqResponseDTO> dtoPage = entities.map(candidateReqMapper::toDto);
        return PageableResponseUtil.fromPage(dtoPage, sort);
    }

    @Override
    public CandidateReqResponseDTO getCandidateReqById(Long id) {
        CandidateReqEntity candidateReqEntity = candidateReqRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate Requirement Not Found"));
        return candidateReqMapper.toDto(candidateReqEntity);
    }

    @Override
    public CandidateReqResponseDTO addCandidateReq(CandidateReqRequestDTO dto) {
        CandidateReqEntity candidateReqEntity = candidateReqMapper.toEntity(dto);
        candidateReqEntity.setCreatedAt(LocalDateTime.now());
        candidateReqEntity.setUpdatedAt(LocalDateTime.now());
        candidateReqEntity = candidateReqRepository.save(candidateReqEntity);
        return new CandidateReqResponseDTO(candidateReqEntity.getId());
    }

    @Override
    public CandidateReqResponseDTO updateCandidateReq(CandidateReqRequestDTO dto) {
        CandidateReqEntity candidateReqEntity = candidateReqMapper.toEntity(dto);
        candidateReqEntity.setUpdatedAt(LocalDateTime.now());
        candidateReqEntity = candidateReqRepository.save(candidateReqEntity);
        return new CandidateReqResponseDTO(candidateReqEntity.getId());
    }

    @Override
    public void deleteCandidateReq(Long id) {
        if (!candidateReqRepository.existsById(id)) {
            throw new RuntimeException("Candidate Requirement Not Found");
        }
        candidateReqRepository.deleteById(id);
    }
}

package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.LevelRequestDTO;
import com.tiep.demoapus.dto.response.LevelResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.PageableResponseUtil;
import com.tiep.demoapus.entity.LevelEntity;
import com.tiep.demoapus.mapper.LevelMapper;
import com.tiep.demoapus.repository.LevelRepository;
import com.tiep.demoapus.service.LevelService;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;
    private final LevelMapper levelMapper;

    @Override
    public PageableResponse<LevelResponseDTO> getAllLevels(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<LevelEntity> levels = levelRepository.findAll(GenericSpecification.searchByCodeOrName(search), pageRequest);
        Page<LevelResponseDTO> dtoPage = levels.map(levelMapper::toDto);
        return PageableResponseUtil.fromPage(dtoPage, sort);
    }

    @Override
    public LevelResponseDTO getLevelById(Long id) {
        LevelEntity levelEntity = levelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Level not found"));
        return levelMapper.toDto(levelEntity);
    }

    @Override
    public LevelResponseDTO addLevel(LevelRequestDTO requestDTO) {
        LevelEntity levelEntity = levelMapper.toEntity(requestDTO);
        levelEntity.setCreatedAt(LocalDateTime.now());
        levelEntity.setUpdatedAt(LocalDateTime.now());
        levelEntity = levelRepository.save(levelEntity);
        return new LevelResponseDTO(levelEntity.getId());
    }

    @Override
    public LevelResponseDTO updateLevel(LevelRequestDTO requestDTO) {
        LevelEntity levelEntity = levelMapper.toEntity(requestDTO);
        levelEntity.setUpdatedAt(LocalDateTime.now());
        levelEntity = levelRepository.save(levelEntity);
        return new LevelResponseDTO(levelEntity.getId());
    }

    @Override
    public void deleteLevelById(Long id) {
        if (!levelRepository.existsById(id)) {
            throw new RuntimeException("Level not found");
        }
        levelRepository.deleteById(id);
    }
}

package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.TagRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponseUtil;
import com.tiep.demoapus.dto.response.ReasonResponseDTO;
import com.tiep.demoapus.dto.response.TagResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.entity.TagEntity;
import com.tiep.demoapus.mapper.TagMapper;
import com.tiep.demoapus.repository.TagRepository;
import com.tiep.demoapus.service.TagService;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public PageableResponse<TagResponseDTO> getAllTags(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith(":DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<TagEntity> tagEntities = tagRepository.findAll(GenericSpecification.searchByName(search), pageable);
        Page<TagResponseDTO> dtoPage = tagEntities.map(tagMapper::toDTO);
        return PageableResponseUtil.fromPage(dtoPage, sort);
    }

    @Override
    public TagResponseDTO getTagById(Long id) {
        TagEntity tagEntity = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        return tagMapper.toDTO(tagEntity);
    }

    @Override
    public TagResponseDTO addTag(TagRequestDTO dto) {
        TagEntity tagEntity = tagMapper.toEntity(dto);
        tagEntity.setCreatedAt(LocalDateTime.now());
        tagEntity.setUpdatedAt(LocalDateTime.now());
        TagEntity savedTagEntity = tagRepository.saveAndFlush(tagEntity);
        return tagMapper.toDTO(savedTagEntity);
    }

    @Override
    public TagResponseDTO updateTag(TagRequestDTO dto) {
        TagEntity tagEntity = tagRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        tagEntity.setName(dto.getName());
        tagEntity.setActive(dto.getActive());
        tagEntity.setUpdatedAt(LocalDateTime.now());
        return tagMapper.toDTO(tagRepository.save(tagEntity));
    }

    @Override
    public void deleteTag(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new RuntimeException("Tag not found");
        }
        tagRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return tagRepository.existsById(id);
    }
}

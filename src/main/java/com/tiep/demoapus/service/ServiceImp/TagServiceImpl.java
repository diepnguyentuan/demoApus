package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.TagRequestDTO;
import com.tiep.demoapus.dto.response.TagResponseDTO;
import com.tiep.demoapus.entity.TagEntity;
import com.tiep.demoapus.mapper.TagMapper;
import com.tiep.demoapus.repository.TagRepository;
import com.tiep.demoapus.service.TagService;
import com.tiep.demoapus.specification.TagSpecification;
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
    public Page<TagResponseDTO> getAllTags(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith(":DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<TagEntity> pageData = tagRepository.findAll(TagSpecification.searchByName(search), pageable);
        return pageData.map(tagMapper::toDTO);
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
        tagEntity.setCreated_at(LocalDateTime.now());
        tagEntity.setUpdated_at(LocalDateTime.now());
        return tagMapper.toDTO(tagRepository.save(tagEntity));
    }

    @Override
    public TagResponseDTO updateTag(Long id, TagRequestDTO dto) {
        TagEntity tagEntity = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        tagEntity.setName(dto.getName());
        tagEntity.setActive(dto.getIsActive());
        tagEntity.setUpdated_at(LocalDateTime.now());
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

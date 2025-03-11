package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.BenefitRequestDTO;
import com.tiep.demoapus.dto.response.BenefitResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.PageableResponseUtil;
import com.tiep.demoapus.entity.BenefitEntity;
import com.tiep.demoapus.entity.BenefitMapEntity;
import com.tiep.demoapus.mapper.BenefitMapper;
import com.tiep.demoapus.repository.BenefitRepository;
import com.tiep.demoapus.service.BenefitService;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BenefitServiceImpl implements BenefitService {

    private final BenefitRepository benefitRepository;
    private final BenefitMapper benefitMapper;

    @Override
    public PageableResponse<BenefitResponseDTO> getBenefits(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<BenefitEntity> entities = benefitRepository.findAll(GenericSpecification.searchByName(search), pageRequest);
        Page<BenefitResponseDTO> toDto = entities.map(benefitMapper::toDto);
        return PageableResponseUtil.fromPage(toDto, sort);
    }

    @Override
    public BenefitResponseDTO getBenefitById(Long id) {
        BenefitEntity entity = benefitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Benefit not found"));
        return benefitMapper.toDto(entity);
    }

    @Override
    public BenefitResponseDTO addBenefit(BenefitRequestDTO benefitRequestDTO) {
        // Map từ DTO sang Entity, trong đó 'lines' được chuyển thành danh sách BenefitMapEntity
        BenefitEntity benefitEntity = benefitMapper.toEntity(benefitRequestDTO);

        // Đảm bảo mỗi BenefitMapEntity có tham chiếu đến BenefitEntity
        if (benefitEntity.getMaps() != null) {
            benefitEntity.getMaps().forEach(map -> map.setBenefit(benefitEntity));
        }

        // Lưu BenefitEntity, nếu cascade được thiết lập thì các BenefitMapEntity cũng được lưu tự động
        BenefitEntity savedEntity = benefitRepository.save(benefitEntity);

        // Mapper sẽ chuyển danh sách maps thành danh sách departments khi trả về
        return new BenefitResponseDTO(savedEntity.getId());
    }

    @Override
    public BenefitResponseDTO updateBenefit(BenefitRequestDTO benefitRequestDTO) {
        // Map từ DTO sang Entity, trong đó 'lines' được chuyển thành danh sách BenefitMapEntity
        BenefitEntity benefitEntity = benefitMapper.toEntity(benefitRequestDTO);

        // Đảm bảo mỗi BenefitMapEntity có tham chiếu đến BenefitEntity
        if (benefitEntity.getMaps() != null) {
            benefitEntity.getMaps().forEach(map -> map.setBenefit(benefitEntity));
        }

        // Lưu BenefitEntity, nếu cascade được thiết lập thì các BenefitMapEntity cũng được lưu tự động
        BenefitEntity savedEntity = benefitRepository.save(benefitEntity);

        // Mapper sẽ chuyển danh sách maps thành danh sách departments khi trả về
        return new BenefitResponseDTO(savedEntity.getId());
    }


    @Override
    public void deleteBenefit(Long id) {
        if (!benefitRepository.existsById(id)) {
            throw new RuntimeException("Benefit not found");
        }
        benefitRepository.deleteById(id);
    }
}

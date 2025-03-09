package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.BenefitRequestDTO;
import com.tiep.demoapus.dto.response.BenefitResponseDTO;
import com.tiep.demoapus.dto.response.DepartmentResponseDTO;
import com.tiep.demoapus.entity.BenefitEntity;
import com.tiep.demoapus.entity.BenefitMapEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BenefitMapper {

    // Map từ DTO yêu cầu (BenefitRequestDTO) sang thực thể (BenefitEntity)
    // Trường 'lines' trong DTO sẽ được chuyển thành danh sách BenefitMapEntity cho thuộc tính 'maps'
    @Mapping(target = "maps", source = "departmentId", qualifiedByName = "convertDepartmentDTOListToBenefitMapEntityList")
    BenefitEntity toEntity(BenefitRequestDTO dto);

    // Map từ thực thể (BenefitEntity) sang DTO phản hồi (BenefitResponseDTO)
    // Trường 'maps' trong entity sẽ được chuyển thành danh sách departments trong DTO
    @Mapping(target = "departments", source = "maps", qualifiedByName = "convertBenefitMapEntityListToDepartmentResponseDTOList")
    BenefitResponseDTO toDto(BenefitEntity entity);

    // Chuyển đổi danh sách các DepartmentDTO (trong trường lines của DTO) thành danh sách BenefitMapEntity
    @Named("convertDepartmentDTOListToBenefitMapEntityList")
    default List<BenefitMapEntity> convertDepartmentDTOListToBenefitMapEntityList(List<com.tiep.demoapus.dto.request.DepartmentDTO> lines) {
        if (lines == null) {
            return null;
        }
        return lines.stream().map(departmentDTO -> {
            BenefitMapEntity mapEntity = new BenefitMapEntity();
            // Gán departmentId từ thuộc tính id của DepartmentDTO
            mapEntity.setDepartmentId(departmentDTO.getId());
            return mapEntity;
        }).toList();
    }

    // Chuyển đổi danh sách BenefitMapEntity thành danh sách DepartmentResponseDTO
    // Ở đây, chỉ map thuộc tính id từ departmentId, các thuộc tính khác có thể bỏ qua
    @Named("convertBenefitMapEntityListToDepartmentResponseDTOList")
    default List<DepartmentResponseDTO> convertBenefitMapEntityListToDepartmentResponseDTOList(List<BenefitMapEntity> maps) {
        if (maps == null) {
            return null;
        }
        return maps.stream().map(mapEntity -> {
            DepartmentResponseDTO dto = new DepartmentResponseDTO();
            dto.setId(mapEntity.getDepartmentId());
            return dto;
        }).toList();
    }
}

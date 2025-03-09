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
    // Ở đây, danh sách các department trong dto được map sang trường maps của entity
    @Mapping(target = "maps", source = "lines", qualifiedByName = "convertDepartmentDTOListToBenefitMapEntityList")
    BenefitEntity toEntity(BenefitRequestDTO dto);

    // Map từ thực thể (BenefitEntity) sang DTO phản hồi (BenefitResponseDTO)
    // Ở đây, danh sách maps của entity được chuyển thành danh sách departments trong dto
    @Mapping(target = "departments", source = "maps", qualifiedByName = "convertBenefitMapEntityListToDepartmentResponseDTOList")
    BenefitResponseDTO toResponseDTO(BenefitEntity entity);

    // Chuyển đổi danh sách DepartmentDTO (trong BenefitRequestDTO) thành danh sách BenefitMapEntity
    @Named("convertDepartmentDTOListToBenefitMapEntityList")
    default List<BenefitMapEntity> convertDepartmentDTOListToBenefitMapEntityList(List<com.tiep.demoapus.dto.request.DepartmentDTO> lines) {
        if (lines == null) {
            return null;
        }
        return lines.stream().map(departmentDTO -> {
            BenefitMapEntity mapEntity = new BenefitMapEntity();
            // Chỉ map thuộc tính departmentId vì BenefitMapEntity không chứa thông tin name
            mapEntity.setDepartmentId(departmentDTO.getId());
            return mapEntity;
        }).toList();
    }

    // Chuyển đổi danh sách BenefitMapEntity thành danh sách DepartmentResponseDTO
    // Ở đây, chỉ map thuộc tính id và bỏ qua trường name
    @Named("convertBenefitMapEntityListToDepartmentResponseDTOList")
    default List<DepartmentResponseDTO> convertBenefitMapEntityListToDepartmentResponseDTOList(List<BenefitMapEntity> maps) {
        if (maps == null) {
            return null;
        }
        return maps.stream().map(mapEntity -> {
            DepartmentResponseDTO dto = new DepartmentResponseDTO();
            dto.setId(mapEntity.getDepartmentId());
            // Không thiết lập trường name, mặc định là null
            return dto;
        }).toList();
    }
}

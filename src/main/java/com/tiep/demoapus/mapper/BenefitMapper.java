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

    // Map từ BenefitEntity sang BenefitResponseDTO
    // Trường "lines" được tạo từ danh sách "maps" (chỉ có departmentId được set)
    @Mapping(target = "lines", source = "maps", qualifiedByName = "mapBenefitMapEntitiesToDepartmentResponseDTOList")
    BenefitResponseDTO toDto(BenefitEntity entity);

    // Map từ BenefitRequestDTO sang BenefitEntity (chuyển danh sách departmentIds thành maps)
    @Mapping(target = "maps", source = "departmentIds", qualifiedByName = "convertDepartmentIdsToBenefitMapEntities")
    BenefitEntity toEntity(BenefitRequestDTO dto);

    @Named("mapBenefitMapEntitiesToDepartmentResponseDTOList")
    default List<DepartmentResponseDTO> mapBenefitMapEntitiesToDepartmentResponseDTOList(List<BenefitMapEntity> maps) {
        if (maps == null || maps.isEmpty()) {
            return List.of();
        }
        return maps.stream()
                .map(mapEntity -> {
                    DepartmentResponseDTO deptDto = new DepartmentResponseDTO();
                    // Set departmentId từ mapping; tên không được cập nhật (sẽ là null)
                    deptDto.setId(mapEntity.getDepartmentId());
                    return deptDto;
                })
                .toList();
    }

    @Named("convertDepartmentIdsToBenefitMapEntities")
    default List<BenefitMapEntity> convertDepartmentIdsToBenefitMapEntities(List<Long> departmentIds) {
        if (departmentIds == null || departmentIds.isEmpty()) {
            return List.of();
        }
        return departmentIds.stream()
                .map(id -> {
                    BenefitMapEntity mapEntity = new BenefitMapEntity();
                    mapEntity.setDepartmentId(id);
                    return mapEntity;
                })
                .toList();
    }
}

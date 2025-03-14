package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.response.DepartmentResponseDTO;
import com.tiep.demoapus.dto.response.JobPositionLineDTO;
import com.tiep.demoapus.dto.response.JobPositionMapResponseDTO;
import com.tiep.demoapus.dto.response.PositionResponseDTO;
import com.tiep.demoapus.entity.JobPositionEntity;
import com.tiep.demoapus.entity.JobPositionMapEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {IndustryJobMapper.class})
public interface JobPositionMapMapper {

    @Mapping(target = "industry", source = "industryEntity")
    @Mapping(target = "lines", ignore = true)
    JobPositionMapResponseDTO toDto(JobPositionEntity entity);

    default JobPositionMapResponseDTO toDtoWithLines(JobPositionEntity entity) {
        JobPositionMapResponseDTO dto = toDto(entity);
        dto.setLines(mapToJobPositionLineDTO(entity.getMaps()));
        return dto;
    }

    default List<JobPositionLineDTO> mapToJobPositionLineDTO(List<JobPositionMapEntity> maps) {
        if (maps == null || maps.isEmpty()) {
            return Collections.emptyList();
        }
        Map<Long, List<JobPositionMapEntity>> grouped = maps.stream()
                .collect(Collectors.groupingBy(JobPositionMapEntity::getDepartmentId));

        List<JobPositionLineDTO> lines = new ArrayList<>();
        for (Map.Entry<Long, List<JobPositionMapEntity>> departmentEntry : grouped.entrySet()) {
            JobPositionLineDTO line = new JobPositionLineDTO();
            DepartmentResponseDTO department = new DepartmentResponseDTO();
            department.setId(departmentEntry.getKey());
            line.setDepartment(department);

            List<PositionResponseDTO> positions = departmentEntry.getValue().stream()
                    .map(mapEntity -> {
                        PositionResponseDTO pos = new PositionResponseDTO();
                        pos.setId(mapEntity.getPositionId());
                        return pos;
                    })
                    .toList();
            line.setPosition(positions);
            lines.add(line);
        }
        return lines;
    }
}

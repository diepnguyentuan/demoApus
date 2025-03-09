package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.DepartmentResponseDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.dto.response.PositionResponseDTO;
import com.tiep.demoapus.entity.JobPositionEntity;
import com.tiep.demoapus.entity.JobPositionMapEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", uses = {IndustryMapper.class})
public interface JobPositionMapper {

    @Mapping(source = "jobPosition.industryEntity", target = "industry")
    @Mapping(target = "lines", expression = "java(mapLines(maps))")
    JobPositionResponseDTO toDTO(JobPositionEntity jobPosition, List<JobPositionMapEntity> maps);

    JobPositionEntity toEntity(JobPositionRequestDTO dto);

    default List<JobPositionResponseDTO.Line> mapLines(List<JobPositionMapEntity> maps) {
        if (maps == null || maps.isEmpty()) {
            return Collections.emptyList();
        }

        // Group các JobPositionMapEntity theo departmentId
        Map<Long, List<JobPositionMapEntity>> grouped = new HashMap<>();
        for (JobPositionMapEntity mapEntity : maps) {
            Long deptId = mapEntity.getDepartmentId();
            if (!grouped.containsKey(deptId)) {
                grouped.put(deptId, new ArrayList<>());
            }
            grouped.get(deptId).add(mapEntity);
        }

        List<JobPositionResponseDTO.Line> lines = new ArrayList<>();
        // Với mỗi nhóm theo department, tạo đối tượng Line với department và danh sách position
        for (Map.Entry<Long, List<JobPositionMapEntity>> entry : grouped.entrySet()) {
            Long departmentId = entry.getKey();
            List<JobPositionMapEntity> groupMaps = entry.getValue();
            List<PositionResponseDTO> positions = new ArrayList<>();

            for (JobPositionMapEntity mapEntity : groupMaps) {
                PositionResponseDTO posDTO = new PositionResponseDTO(mapEntity.getPositionId(), null);
                boolean duplicate = false;
                // Kiểm tra xem đã có position với id này trong danh sách chưa
                for (PositionResponseDTO existing : positions) {
                    if (existing.getId().equals(posDTO.getId())) {
                        duplicate = true;
                        break;
                    }
                }
                if (!duplicate) {
                    positions.add(posDTO);
                }
            }

            DepartmentResponseDTO deptDTO = new DepartmentResponseDTO(departmentId);
            JobPositionResponseDTO.Line line = new JobPositionResponseDTO.Line(deptDTO, positions);
            lines.add(line);
        }
        return lines;
    }
}

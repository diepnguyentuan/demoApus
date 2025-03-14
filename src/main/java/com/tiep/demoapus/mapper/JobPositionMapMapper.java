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

/**
 * Mapper để ánh xạ dữ liệu từ bảng job_position (chứa thông tin industry)
 * và bảng job_position_map (chỉ chứa mapping giữa department và position)
 * sang DTO JobPositionMapResponseDTO.
 *
 * Lưu ý: Field industry trong JobPositionMapResponseDTO được lấy từ
 * JobPositionEntity.industryEntity (từ bảng job_position) vì job_position_map không có cột industry.
 */
@Mapper(componentModel = "spring", uses = {IndustryJobMapper.class})
public interface JobPositionMapMapper {

    @Mapping(target = "industry", source = "industryEntity")
    // Các trường id, code, name, description, active được ánh xạ tự động theo tên.
    // Field lines sẽ được thiết lập trong default method bên dưới.
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
        // Nhóm các bản ghi theo departmentId.
        Map<Long, List<JobPositionMapEntity>> grouped = maps.stream()
                .collect(Collectors.groupingBy(JobPositionMapEntity::getDepartmentId));

        List<JobPositionLineDTO> lines = new ArrayList<>();
        for (Map.Entry<Long, List<JobPositionMapEntity>> entry : grouped.entrySet()) {
            JobPositionLineDTO line = new JobPositionLineDTO();
            // Tạo đối tượng DepartmentResponseDTO chỉ với id.
            DepartmentResponseDTO department = new DepartmentResponseDTO();
            department.setId(entry.getKey());
            line.setDepartment(department);

            // Ánh xạ danh sách position chỉ với id.
            List<PositionResponseDTO> positions = entry.getValue().stream()
                    .map(entity -> {
                        PositionResponseDTO pos = new PositionResponseDTO();
                        pos.setId(entity.getPositionId());
                        return pos;
                    })
                    .collect(Collectors.toList());
            line.setPosition(positions);
            lines.add(line);
        }
        return lines;
    }
}

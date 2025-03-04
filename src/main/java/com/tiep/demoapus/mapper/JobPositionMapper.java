package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO.Line;
import com.tiep.demoapus.dto.response.DepartmentResponseDTO;
import com.tiep.demoapus.dto.response.PositionResponseDTO;
import com.tiep.demoapus.dto.response.IndustryResponseDTO;
import com.tiep.demoapus.entity.JobPosition;
import com.tiep.demoapus.entity.JobPositionMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class JobPositionMapper {

    public JobPositionResponseDTO toDTO(JobPosition jobPosition, List<JobPositionMap> jobPositionMaps) {
        // Mỗi bản ghi JobPositionMap sẽ ánh xạ thành một line với department và position
        List<Line> lines = jobPositionMaps.stream()
                .map(map -> new Line(
                        // Tạo đối tượng DepartmentResponseDTO từ departmentId (đây có thể được lấy từ service khác)
                        new DepartmentResponseDTO(map.getDepartmentId(), "Department Name"),
                        // Tạo đối tượng PositionResponseDTO từ positionId
                        new PositionResponseDTO(map.getPositionId(), "Position Name")
                ))
                .collect(Collectors.toList());

        // Ánh xạ Industry sang IndustryResponseDTO sử dụng IndustryMapper
        IndustryResponseDTO industryDTO = IndustryMapper.toDTO(jobPosition.getIndustry());

        JobPositionResponseDTO dto = new JobPositionResponseDTO();
        dto.setId(jobPosition.getId());
        dto.setCode(jobPosition.getCode());
        dto.setName(jobPosition.getName());
        dto.setIndustry(industryDTO);  // Đổi trường từ major sang industry
        dto.setDescription(jobPosition.getDescription());
        dto.setIs_active(jobPosition.isActive());
        dto.setLines(lines);
        dto.setCreatedAt(jobPosition.getCreatedAt());
        dto.setUpdatedAt(jobPosition.getUpdatedAt());
        return dto;
    }
}

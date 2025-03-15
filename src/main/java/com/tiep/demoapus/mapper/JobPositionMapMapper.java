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
import java.util.List;

@Mapper(componentModel = "spring", uses = {IndustryJobMapper.class})
public interface JobPositionMapMapper {

    // Chuyển đổi từ entity sang DTO cơ bản (không bao gồm danh sách line)
    @Mapping(target = "industry", source = "industryEntity")
    @Mapping(target = "lines", ignore = true)
    JobPositionMapResponseDTO toDto(JobPositionEntity entity);

    // Sau đó thêm thông tin line mapping (sử dụng cách viết đơn giản với for)
    default JobPositionMapResponseDTO toDtoWithLines(JobPositionEntity entity) {
        JobPositionMapResponseDTO dto = toDto(entity);
        dto.setLines(convertMapsToLines(entity.getMaps()));
        return dto;
    }

    // Chuyển danh sách JobPositionMapEntity thành danh sách JobPositionLineDTO theo cách đơn giản
    default List<JobPositionLineDTO> convertMapsToLines(List<JobPositionMapEntity> maps) {
        List<JobPositionLineDTO> lines = new ArrayList<>();
        if (maps == null || maps.isEmpty()) {
            return lines;
        }
        // Nhóm các map theo departmentId theo cách "thủ công"
        // Mỗi department sẽ có một JobPositionLineDTO
        for (JobPositionMapEntity mapEntity : maps) {
            // Kiểm tra nếu đã có line cho department này
            JobPositionLineDTO foundLine = null;
            for (JobPositionLineDTO line : lines) {
                if (line.getDepartment().getId().equals(mapEntity.getDepartmentId())) {
                    foundLine = line;
                    break;
                }
            }
            if (foundLine == null) {
                // Nếu chưa có, tạo mới một line
                foundLine = new JobPositionLineDTO();
                DepartmentResponseDTO dept = new DepartmentResponseDTO();
                dept.setId(mapEntity.getDepartmentId());
                foundLine.setDepartment(dept);
                foundLine.setPosition(new ArrayList<>());
                lines.add(foundLine);
            }
            // Tạo một PositionResponseDTO với id
            PositionResponseDTO pos = new PositionResponseDTO();
            pos.setId(mapEntity.getPositionId());
            foundLine.getPosition().add(pos);
        }
        return lines;
    }
}

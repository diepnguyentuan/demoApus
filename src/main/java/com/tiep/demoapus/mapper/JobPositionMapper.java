package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.entity.JobPositionEntity;
import com.tiep.demoapus.entity.JobPositionMapEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IndustryMapper.class})
public interface JobPositionMapper {
    // Sử dụng list các JobPositionMap để ánh xạ thông tin line (department/position)\n
    JobPositionResponseDTO toDTO(JobPositionEntity jobPosition, List<JobPositionMapEntity> maps);

    // Nếu cần chuyển từ RequestDTO sang Entity (không map thông tin departmentPositions vì xử lý riêng trong service)\n
    JobPositionEntity toEntity(JobPositionRequestDTO dto);
}

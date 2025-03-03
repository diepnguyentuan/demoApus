package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.response.*;
import com.tiep.demoapus.entity.JobPosition;

import java.util.List;
import java.util.stream.Collectors;

public class JobPositionMapper {
    public static JobPositionResponseDTO toDTO(JobPosition entity) {
        // Chuyển đổi industry thành IndustryResponseDTO
        IndustryResponseDTO major = new IndustryResponseDTO(
                entity.getIndustry().getId(),
                entity.getIndustry().getCode(),
                entity.getIndustry().getName()
        );

        // Chuyển đổi danh sách JobPositionMap thành danh sách JobPositionMapResponseDTO
        List<JobPositionMapResponseDTO> lines = entity.getJobPositionMaps().stream().map(map ->
                new JobPositionMapResponseDTO(
                        new DepartmentResponseDTO(map.getDepartmentId(), "Department Name"), // Lấy tên từ service hoặc database nếu cần
                        new PositionResponseDTO(map.getPositionId(), "Position Name") // Lấy tên từ service hoặc database nếu cần
                )
        ).collect(Collectors.toList());

        return new JobPositionResponseDTO(
                entity.getCode(),
                entity.getName(),
                major,
                entity.getDescription(),
                entity.isActive(),
                lines
        );
    }
}

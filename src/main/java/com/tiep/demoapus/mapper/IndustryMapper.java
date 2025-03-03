package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.IndustryRequestDTO;
import com.tiep.demoapus.dto.response.IndustryResponseDTO;
import com.tiep.demoapus.entity.Industry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp Mapper để chuyển đổi giữa Industry entity và các DTO.
 */
public class IndustryMapper {

    /**
     * Chuyển đổi một đối tượng Industry sang IndustryDTO.
     *
     * @param industry đối tượng Industry
     * @return đối tượng IndustryDTO chứa dữ liệu cần thiết
     */
    public static IndustryResponseDTO toDTO(Industry industry) {
        if (industry == null) return null;
        IndustryResponseDTO dto = new IndustryResponseDTO();
        dto.setId(industry.getId());
        dto.setCode(industry.getCode());
        dto.setName(industry.getName());
        dto.setIsActive(industry.getActive());
        return dto;
    }

    /**
     * Chuyển đổi danh sách các Industry sang danh sách IndustryDTO.
     *
     * @param industries danh sách Industry
     * @return danh sách IndustryDTO
     */
    public static List<IndustryResponseDTO> toDTOList(List<Industry> industries) {
        List<IndustryResponseDTO> dtoList = new ArrayList<>();
        if (industries != null) {
            for (Industry ind : industries) {
                dtoList.add(toDTO(ind));
            }
        }
        return dtoList;
    }

    /**
     * Chuyển đổi một IndustryRequestDTO sang đối tượng Industry.
     * Lưu ý: Trường id bị bỏ qua vì nó được sinh tự động khi lưu vào cơ sở dữ liệu.
     *
     * @param dto IndustryRequestDTO nhận từ client
     * @return đối tượng Industry để lưu vào DB
     */
    public static Industry toEntity(IndustryRequestDTO dto) {
        if (dto == null) return null;
        Industry industry = new Industry();
        // Không set id vì id sẽ được auto-generate
        industry.setCode(dto.getCode());
        industry.setName(dto.getName());
        industry.setActive(dto.getIsActive());
        industry.setDescription(dto.getDescription());
        industry.setCreatedAt(LocalDateTime.now()); // Gán thời gian hiện tại
        return industry;
    }
}

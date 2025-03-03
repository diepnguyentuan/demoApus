package com.tiep.demoapus.dto.response;

/**
 * Data Transfer Object dùng để trả về dữ liệu Industry cho client.
 * Chỉ chứa các trường cơ bản không bao gồm description.
 */
public class IndustryResponseDTO {
    private Long id;
    private String code;
    private String name;
    private Boolean isActive;

    // Constructor không đối số
    public IndustryResponseDTO() {}

    // Constructor đầy đủ các tham số
    public IndustryResponseDTO(Long id, String code, String name, Boolean isActive) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.isActive = isActive;
    }

    public IndustryResponseDTO(Long id, String code, String name) {
    }

    // Getter và Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}

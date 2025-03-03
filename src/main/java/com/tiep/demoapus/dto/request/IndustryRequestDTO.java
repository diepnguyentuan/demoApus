package com.tiep.demoapus.dto.request;

/**
 * Data Transfer Object dùng để nhận dữ liệu của Industry từ phía client.
 * Dữ liệu này được dùng cho việc tạo mới hoặc cập nhật một Industry.
 */
public class IndustryRequestDTO {
    private Long id;
    private String code;
    private String name;
    private Boolean isActive;
    private String description;

    // Constructor không đối số
    public IndustryRequestDTO() {}

    // Constructor đầy đủ các tham số
    public IndustryRequestDTO(Long id, String code, String name, Boolean isActive, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.isActive = isActive;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

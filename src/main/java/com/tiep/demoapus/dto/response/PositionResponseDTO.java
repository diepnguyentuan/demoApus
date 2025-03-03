package com.tiep.demoapus.dto.response;


public class PositionResponseDTO {
    private Long id;
    private String name;

    // Constructors
    public PositionResponseDTO() {}

    public PositionResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


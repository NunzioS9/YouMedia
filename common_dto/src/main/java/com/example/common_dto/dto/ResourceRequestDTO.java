package com.example.common_dto.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ResourceRequestDTO {

    private Integer customerId;
    private Integer resourceId;
    private Integer views;
    private UUID sagaId;

}

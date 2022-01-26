package com.example.common_dto.dto;

import com.example.common_dto.enums.ResourceStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class ResourceResponseDTO {

    private UUID sagaId;
    private Integer customerId;
    private Integer resourceId;
    private Integer views;
    private ResourceStatus status;

}

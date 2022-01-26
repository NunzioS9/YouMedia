package com.example.common_dto.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CustomerRequestDTO {

    private Integer customerId;
    private Integer resourceId;
    private UUID sagaId;

}
package com.example.common_dto.dto;

import com.example.common_dto.enums.CustomerStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class CustomerResponseDTO {

    private UUID sagaId;
    private Integer customerId;
    private Integer resourceId;
    private Double amount;
    private CustomerStatus status;

}

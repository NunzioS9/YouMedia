package com.example.common_dto.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PaymentRequestDTO {
    private Integer customerId;
    private UUID sagaId;
    private Double amount;
}

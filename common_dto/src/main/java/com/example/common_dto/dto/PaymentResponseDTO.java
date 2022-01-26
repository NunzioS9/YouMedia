package com.example.common_dto.dto;

import com.example.common_dto.enums.PaymentStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class PaymentResponseDTO {
    private Integer customerId;
    private UUID sagaId;
    private Double amount;
    private Double crediti;
    private PaymentStatus status;
}

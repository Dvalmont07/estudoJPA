package com.estudoJPA.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {
    private BigDecimal value;
    private Long senderId;
    private Long receiverId;
}
package com.example.desafioitau.domain.transaction;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private BigDecimal amount;
    private Timestamp dateTime;
}

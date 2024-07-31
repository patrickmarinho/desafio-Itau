package com.example.desafioitau.domain.transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {
    private Long id;
    private String sender;
    private String receiver;
    private BigDecimal amount;
    private Timestamp dateTime;
}

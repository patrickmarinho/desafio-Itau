package com.example.desafioitau.domain.transaction;

import lombok.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private Float amount;
    private Timestamp dateTime;
}

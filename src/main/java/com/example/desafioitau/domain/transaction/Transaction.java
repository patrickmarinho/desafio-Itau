package com.example.desafioitau.domain.transaction;

import lombok.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private Float amount;
    private OffsetDateTime dateTime;
}

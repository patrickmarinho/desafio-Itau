package com.example.desafioitau.domain.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {
    private Float count;
    private Float sum;
    private Float avg;
    private Float min;
    private Float max;
}

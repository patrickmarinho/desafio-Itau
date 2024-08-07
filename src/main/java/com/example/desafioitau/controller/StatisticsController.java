package com.example.desafioitau.controller;

import com.example.desafioitau.domain.statistics.Statistics;

import com.example.desafioitau.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/statistics", produces = "application/json")
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    @GetMapping()
    public ResponseEntity<Statistics> getStatistics() {
        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.getStatistics());
    }
}

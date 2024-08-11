package com.example.desafioitau.service;

import com.example.desafioitau.domain.statistics.Statistics;
import com.example.desafioitau.domain.transaction.Transaction;
import com.example.desafioitau.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ActiveProfiles("test")
class StatisticsServiceTest {

    @Mock
    TransactionRepository transactionRepository;

    @Autowired
    @InjectMocks
    StatisticsService statisticsService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getStatisticsWithTransactionsStats() {
        //Arrange
        OffsetDateTime dateTime = OffsetDateTime.now();
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(15f, dateTime));
        transactions.add(new Transaction(20f, dateTime));
        transactions.add(new Transaction(30f, dateTime));
        doReturn(transactions).when(transactionRepository).getLast60SecondsTransactions();

        //Act
        DoubleSummaryStatistics doubleSummaryStatistics = new DoubleSummaryStatistics();
        transactions.forEach(transaction -> doubleSummaryStatistics.accept(transaction.getAmount()));
        Statistics statistics = statisticsService.getStatistics();

        //Assert
        assertNotNull(statistics);
        assertEquals((float) doubleSummaryStatistics.getCount(), statistics.getCount());
        assertEquals((float) doubleSummaryStatistics.getSum(), statistics.getSum());
        assertEquals((float) doubleSummaryStatistics.getAverage(), statistics.getAvg());
        assertEquals((float) doubleSummaryStatistics.getMin(), statistics.getMin());
        assertEquals((float) doubleSummaryStatistics.getMax(), statistics.getMax());
    }

    @Test
    void getStatisticsWithNoTransactionsStats(){
        //Arrange
        doReturn(Collections.emptyList()).when(transactionRepository).getLast60SecondsTransactions();

        //Act
        Statistics statistics = statisticsService.getStatistics();

        //Assert
        assertNotNull(statistics);
        assertEquals(0, statistics.getCount());
        assertEquals(0, statistics.getSum());
        assertEquals(0, statistics.getAvg());
        assertEquals(0, statistics.getMin());
        assertEquals(0, statistics.getMax());
    }
}
package com.example.desafioitau.service;

import com.example.desafioitau.domain.statistics.Statistics;
import com.example.desafioitau.domain.transaction.Transaction;
import com.example.desafioitau.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

@Service
public class StatisticsService {
    @Autowired
    private TransactionRepository transactionRepository;

    private final Statistics statistics = new Statistics();

    //Get Statistic
    public Statistics getStatistics(){

        if(transactionRepository.getLast60SecondsTransactions().isEmpty()){
            statistics.setCount((float) 0);
            statistics.setSum((float) 0);
            statistics.setAvg((float) 0);
            statistics.setMin((float) 0);
            statistics.setMax((float) 0);
        }else{
            DoubleSummaryStatistics doubleSummaryStatistics = transactionRepository.getLast60SecondsTransactions().stream()
                    .collect(Collectors.summarizingDouble(Transaction::getAmount));

            statistics.setCount((float) doubleSummaryStatistics.getCount());
            statistics.setSum((float) doubleSummaryStatistics.getSum());
            statistics.setAvg((float) doubleSummaryStatistics.getAverage());
            statistics.setMin((float) doubleSummaryStatistics.getMin());
            statistics.setMax((float) doubleSummaryStatistics.getMax());
        }
        return statistics;
    }
}

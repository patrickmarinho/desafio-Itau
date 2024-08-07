package com.example.desafioitau.repository;

import com.example.desafioitau.domain.transaction.Transaction;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getTransactions(){
        return transactions;
    }

    public List<Transaction> getLast60SecondsTransactions(){
        OffsetDateTime oneMinuteAgo = OffsetDateTime.now().minusSeconds(60);
        List<Transaction> last60SecondTransactions = getTransactions().stream()
                .filter(transaction -> transaction.getDateTime().isAfter(oneMinuteAgo) || transaction.getDateTime().equals(oneMinuteAgo))
                .collect(Collectors.toList());

        return last60SecondTransactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void deleteTransactions(){
        transactions.clear();
    }
}

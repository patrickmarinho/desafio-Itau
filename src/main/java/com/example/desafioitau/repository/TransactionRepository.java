package com.example.desafioitau.repository;

import com.example.desafioitau.domain.transaction.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void deleteTransactions(){
        transactions.clear();
    }
}

package com.example.desafioitau.service;

import com.example.desafioitau.domain.transaction.Transaction;
import com.example.desafioitau.exceptions.InvalidRequestException;
import com.example.desafioitau.exceptions.UnprocessableEntityException;
import com.example.desafioitau.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    //POST
    public Transaction newTransaction(Transaction transaction){

        OffsetDateTime currentNow = OffsetDateTime.now();
        if (transaction.getAmount() == null ||
                transaction.getDateTime() == null ||
                transaction.getDateTime().isAfter(currentNow)) {
            throw new InvalidRequestException();
        }

        if(transaction.getAmount() <= 0){
            throw new UnprocessableEntityException();
        }

        transactionRepository.addTransaction(transaction);
        return transaction;
    }

    //Delete
    public void deleteTransaction(){
        transactionRepository.deleteTransactions();
        return;
    }
}

package com.example.desafioitau.service;

import com.example.desafioitau.domain.transaction.Transaction;
import com.example.desafioitau.exceptions.InvalidRequestException;
import com.example.desafioitau.exceptions.UnprocessableEntityException;
import com.example.desafioitau.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class TransactionService {
    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);
    @Autowired
    private TransactionRepository transactionRepository;

    //POST
    public Transaction newTransaction(Transaction transaction){
        log.info("Received new Transaction request.");
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
        log.info("Transaction successfully processed.");
        return transaction;
    }

    //Delete
    public void deleteTransaction(){
        log.info("Cleaning all transactions.");
        transactionRepository.deleteTransactions();
        log.info("All transactions have been cleaned.");
        return;
    }
}

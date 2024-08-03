package com.example.desafioitau.service;

import com.example.desafioitau.domain.transaction.Transaction;
import com.example.desafioitau.exceptions.InvalidRequestException;
import com.example.desafioitau.exceptions.UnprocessableEntityException;
import com.example.desafioitau.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    //POST
    public Transaction newTransaction(Transaction transaction){
        if(transaction.getAmount() <= 0){
            throw new UnprocessableEntityException();
        }

        LocalDateTime currentDate = LocalDateTime.now();
        if(transaction.getDateTime().after(Timestamp.valueOf(currentDate))){
            throw new InvalidRequestException();
        }

        transactionRepository.addTransaction(transaction);
        return transaction;
    }
}

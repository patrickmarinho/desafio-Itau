package com.example.desafioitau.controller;

import com.example.desafioitau.domain.transaction.Transaction;
import com.example.desafioitau.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transaction", consumes = "application/json", produces = "application/json")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping()
    public Transaction newTransaction(@RequestBody Transaction transaction){

        return transactionService.newTransaction(transaction);
    }

}

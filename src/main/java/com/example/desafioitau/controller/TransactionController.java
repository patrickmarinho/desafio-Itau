package com.example.desafioitau.controller;

import com.example.desafioitau.domain.transaction.Transaction;
import com.example.desafioitau.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transaction", produces = "application/json")
public class TransactionController {
    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
    @Autowired
    TransactionService transactionService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Void> newTransaction(@RequestBody Transaction transaction){
        transactionService.newTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteTransaction(){
        transactionService.deleteTransaction();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

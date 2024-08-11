package com.example.desafioitau.service;

import com.example.desafioitau.domain.transaction.Transaction;
import com.example.desafioitau.exceptions.InvalidRequestException;
import com.example.desafioitau.exceptions.UnprocessableEntityException;
import com.example.desafioitau.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class TransactionServiceTest {

    @Mock
    TransactionRepository transactionRepository;

    @Autowired
    @InjectMocks
    TransactionService transactionService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should add new Transaction with sucess")
    void ShouldAddANewTransactionWithSucess() {
        //Arrange
        OffsetDateTime dateTime = OffsetDateTime.parse("2024-08-10T19:52:00.789-03:00");
        Transaction transaction = new Transaction(1.0f,dateTime);
        doNothing().when(transactionRepository).addTransaction(transaction);

        //Act
        Transaction result = transactionService.newTransaction(transaction);

        //Assert
        assertNotNull(transactionService);
        assertEquals(transaction, result);
        verify(transactionRepository, times(1)).addTransaction(transaction);
    }

    @Test
    @DisplayName("Should throw InvalidRequestException when unable to add a new Transaction")
    void shouldThrowInvalidRequestExceptionWhenUnableToAddNewTransaction(){
        //Arrange
        OffsetDateTime futureDateTime = OffsetDateTime.parse("2150-08-10T19:52:00.789-03:00");
        Transaction transaction = new Transaction(100f, futureDateTime);

        //Act & Assert
        assertThrows(InvalidRequestException.class, () ->{
            transactionService.newTransaction(transaction);
        });

    }

    @Test
    @DisplayName("Should throw UnprocessableEntityException when unable to add a new Transaction")
    void shouldThrowUnprocessableEntityExceptionWhenUnableToAddNewTransaction(){
        //Arrange
        OffsetDateTime dateTime = OffsetDateTime.parse("2024-08-10T19:52:00.789-03:00");
        Transaction transaction = new Transaction(0f, dateTime);
        doNothing().when(transactionRepository).addTransaction(transaction);

        //Act & Assert
        assertThrows(UnprocessableEntityException.class, () ->{
            transactionService.newTransaction(transaction);
        });
    }

    @Test
    @DisplayName("Should delete all Transactions")
    void deleteTransaction() {
        //Act
        transactionService.deleteTransaction();
        //Assert
        verify(transactionRepository, times(1)).deleteTransactions();
    }
}
package cz.account.transactiontransparentaccount.controllers;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cz.account.transactiontransparentaccount.databaze.service.TransactionService;
import cz.account.web.model.Transactions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AccountControllersTest {

    @InjectMocks
    AccountControllers accountControllers;
    
    @Mock
    TransactionService transactionService;

    @Test
    public void testGetTransaction_whenTransactionsFound() {
        MockitoAnnotations.initMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        Integer accountNumber = 1234;
        
        Transactions transaction1 = new Transactions();
        Transactions transaction2 = new Transactions();
        List<Transactions> transactions = Arrays.asList(transaction1, transaction2);
        
        when(transactionService.getAllTransactionsByAccountNumber(accountNumber)).thenReturn(transactions);
        
        ResponseEntity<List<Transactions>> responseEntity = accountControllers.getTransaction(accountNumber);
        
        assertSame(transactions, responseEntity.getBody());
    }

    @Test
    public void testGetTransaction_whenTransactionsNotFound() {
        MockitoAnnotations.initMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        Integer accountNumber = 1234;
        
        when(transactionService.getAllTransactionsByAccountNumber(accountNumber)).thenReturn(Arrays.asList());
        
        ResponseEntity<List<Transactions>> responseEntity = accountControllers.getTransaction(accountNumber);
        
	    assertTrue(responseEntity.getStatusCode().is4xxClientError(), "Expected 4xx client error");
    }
}
package cz.account.transactiontransparentaccount.databaze.service;

import cz.account.transactiontransparentaccount.databaze.models.Transaction;
import cz.account.transactiontransparentaccount.databaze.repozitory.TransactionRepository;
import cz.account.web.model.Amount;
import cz.account.web.model.Transactions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class TransactionServiceTest {
  
    private final TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);
    private final TransactionService transactionService = new TransactionService(transactionRepository);

    @Test
    public void getAllTransactionsByAccountNumberTest() {

        Transaction t1 = getTransaction();

        when(transactionRepository.getTransactionsByOwnAccountNumber("12345")).thenReturn(Optional.of(Collections.singletonList(t1)));

        var transactions = transactionService.getAllTransactionsByAccountNumber(12345);

        assertEquals(1, transactions.size());
        assertEquals("transactionId", transactions.get(0).getTransactionId());
    }

    @Test
    public void getAllTransactionsByAccountNumber_NoTransactionsTest() {

        when(transactionRepository.getTransactionsByOwnAccountNumber("12345")).thenReturn(Optional.empty());

        var transactions = transactionService.getAllTransactionsByAccountNumber(12345);

        assertEquals(0, transactions.size());
    }

    private Transaction getTransaction() {
        var transaction = new Transaction();
        transaction.setAmount(50.0);
        transaction.setCurrency("USD");
        transaction.setId("1");
        transaction.setBankref("bankref");
        transaction.setTransactionId("transactionId");
        transaction.setBookingDate(Instant.now());
        transaction.setPostingDate(Instant.now());
        transaction.setCreditDebitIndicator("CRDT");
        transaction.setOwnAccountNumber("12345");
        transaction.setDetail1("detail1");
        transaction.setDetail2("detail2");
        transaction.setDetail3("detail3");
        transaction.setDetail4("detail4");
        transaction.setProductBankRef("productBankRef");
        transaction.setSpecificSymbol("specificSymbol");
        transaction.setVariableSymbol("variableSymbol");
        
        return transaction;
    }
}
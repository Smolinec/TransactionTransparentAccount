package cz.account.transactiontransparentaccount.controllers;

import cz.account.transactiontransparentaccount.databaze.service.TransactionService;
import cz.account.web.AccountsApi;
import cz.account.web.model.Account;
import cz.account.web.model.Balance;
import cz.account.web.model.Transactions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("v1/")
public class AccountControllers implements AccountsApi {

    private final TransactionService transactionService;

    @Override
    public ResponseEntity<List<Account>> getAccounts() {
        return AccountsApi.super.getAccounts();
    }

    @Override
    public ResponseEntity<Balance> getBalance(Integer accountNumber) {
        return AccountsApi.super.getBalance(accountNumber);
    }

    @Override
    public ResponseEntity<List<Transactions>> getTransaction(Integer accountNumber) {
        List<Transactions> transactions = transactionService.getAllTransactionsByAccountNumber(accountNumber);
        if (transactions.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transactions);
    }
}

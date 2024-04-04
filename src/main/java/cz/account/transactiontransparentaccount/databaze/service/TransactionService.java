package cz.account.transactiontransparentaccount.databaze.service;

import cz.account.transactiontransparentaccount.databaze.models.Transaction;
import cz.account.transactiontransparentaccount.databaze.repozitory.TransactionRepository;
import cz.account.web.model.Amount;
import cz.account.web.model.CounterPartyAccount;
import cz.account.web.model.Details;
import cz.account.web.model.Transactions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public List<Transactions> getAllTransactionsByAccountNumber(Integer accountNumber) {
        return transactionRepository.getTransactionsByOwnAccountNumber(String.valueOf(accountNumber))
                .filter(transactions -> !transactions.isEmpty())
                .map(transactions -> transactions.stream()
                        .map(TransactionService::fromDatabaseTransaction)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public static Transactions fromDatabaseTransaction(Transaction transactionFromDatabase) {
        Transactions transactions = new Transactions();
        Amount amount = new Amount();
        amount.setValue(transactionFromDatabase.getAmount().floatValue());
        amount.setCurrency(transactionFromDatabase.getCurrency());

        transactions.setAmount(amount);
        transactions.setId(transactionFromDatabase.getId());
        transactions.setBankref(transactionFromDatabase.getBankref());
        transactions.setTransactionId(transactionFromDatabase.getTransactionId());
        transactions.setBookingDate(transactionFromDatabase.getBookingDate()
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        transactions.setPostingDate(transactionFromDatabase.getPostingDate()
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        transactions.setCreditDebitIndicator(transactionFromDatabase.getCreditDebitIndicator());
        transactions.setOwnAccountNumber(transactionFromDatabase.getOwnAccountNumber());

        CounterPartyAccount counterPartyAccount = new CounterPartyAccount();
        counterPartyAccount.setAccountName(transactionFromDatabase.getCounterPartyAccountEntity().getName());
        counterPartyAccount.setAccountNumber(transactionFromDatabase.getCounterPartyAccountEntity().getNumber());
        counterPartyAccount.setBankCode(transactionFromDatabase.getCounterPartyAccountEntity().getCode());

        transactions.setCounterPartyAccount(counterPartyAccount);

        Details details = new Details();
        details.setDetail1(transactionFromDatabase.getDetail1());
        details.setDetail2(transactionFromDatabase.getDetail2());
        details.setDetail3(transactionFromDatabase.getDetail3());
        details.setDetail4(transactionFromDatabase.getDetail4());

        transactions.setDetails(details);
        transactions.productBankRef(transactionFromDatabase.getProductBankRef());
        transactions.setSpecificSymbol(transactionFromDatabase.getSpecificSymbol());
        transactions.setVariableSymbol(transactionFromDatabase.getVariableSymbol());
        transactions.setTransactionType(String.format("%s%s",
                transactionFromDatabase.getTransactionTypeEntity().getType(),
                transactionFromDatabase.getTransactionTypeEntity().getCode()
        ));
        return transactions;
    }
}
package cz.account.transactiontransparentaccount.databaze.repozitory;

import cz.account.transactiontransparentaccount.databaze.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<List<Transaction>> getTransactionsByOwnAccountNumber(String ownAccountNumber);
}

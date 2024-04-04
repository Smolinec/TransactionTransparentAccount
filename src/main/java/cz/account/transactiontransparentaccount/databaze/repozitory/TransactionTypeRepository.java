package cz.account.transactiontransparentaccount.databaze.repozitory;

import cz.account.transactiontransparentaccount.databaze.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
}

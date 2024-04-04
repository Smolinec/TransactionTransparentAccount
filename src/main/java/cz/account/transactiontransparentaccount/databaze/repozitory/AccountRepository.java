package cz.account.transactiontransparentaccount.databaze.repozitory;

import cz.account.transactiontransparentaccount.databaze.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}

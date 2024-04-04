package cz.account.transactiontransparentaccount.databaze.repozitory;

import cz.account.transactiontransparentaccount.databaze.models.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {
}

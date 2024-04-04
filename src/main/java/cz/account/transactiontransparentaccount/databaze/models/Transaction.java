package cz.account.transactiontransparentaccount.databaze.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trxId;

    private BigDecimal amount;

    private String currency;

    private String id;

    private String bankref;

    private String transactionId;

    private Date bookingDate;

    private Date postingDate;

    private String creditDebitIndicator;

    private String ownAccountNumber;

    private String detail1;

    private String detail2;

    private String detail3;

    private String detail4;

    private String productBankRef;

    private String constantSymbol;

    private String specificSymbol;

    private String variableSymbol;

    @ManyToOne
    @JoinColumn(name = "counterPartyAccount")
    private Account counterPartyAccountEntity;

    @ManyToOne
    @JoinColumn(name = "transactionType")
    private TransactionType transactionTypeEntity;

    @ManyToOne
    @JoinColumn(name = "statement")
    private Statement statementEntity;
}

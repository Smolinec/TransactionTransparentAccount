package cz.account.transactiontransparentaccount.databaze.models;

import jakarta.persistence.*;

@Entity
@Table(name = "statement")
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statementId;

    private String number;

    private String period;

    private String description;
}

package dev.rafaelbarragan.api.finanza.domain.transaction.entity;

import dev.rafaelbarragan.api.finanza.domain.account.entity.Account;
import dev.rafaelbarragan.api.finanza.domain.enums.TransactionType;
import dev.rafaelbarragan.api.finanza.domain.transaction.dto.TransactionCreate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "transactions")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;

    @OneToOne(fetch = FetchType.EAGER)
    private Account destinationAccount;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String description;

    private LocalDateTime transactionDate;

    private LocalDateTime createdAt;

    private Boolean cancelled;

    public Transaction(TransactionCreate create, Account account, Account destinationAccount) {
        this.account = account;
        this.destinationAccount = destinationAccount;
        this.amount = create.amount();
        this.transactionType = create.transactionType();
        this.description = create.description();
        this.transactionDate = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
        this.cancelled = false;
    }

    public void cancelar() {
        this.cancelled = true;
    }
}

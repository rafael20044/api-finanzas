package dev.rafaelbarragan.api.finanza.domain.account.entity;

import dev.rafaelbarragan.api.finanza.domain.account.dto.AccountCreate;
import dev.rafaelbarragan.api.finanza.domain.account.dto.AccountEdit;
import dev.rafaelbarragan.api.finanza.domain.enums.AccountType;
import dev.rafaelbarragan.api.finanza.domain.enums.TransactionType;
import dev.rafaelbarragan.api.finanza.domain.transaction.entity.Transaction;
import dev.rafaelbarragan.api.finanza.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "accounts")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String accountName;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Double balance;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public Account(AccountCreate create, User user) {
        this.user = user;
        this.accountName = create.accountName();
        this.accountType = create.accountType();
        this.balance = 0.0;
    }

    public void update(AccountEdit edit){
        this.accountName = edit.accountName();
    }

    public void transaction(TransactionType transactionType, Double amount, Account destinationAccount){

        switch (transactionType){
            case INCOME -> incomo(amount);
            case EXPENSE -> expense(amount);
            case TRANSFER -> transfer(amount, destinationAccount);
        }
    }

    private void incomo(Double amount){
        switch (this.accountType){
            case CHECKING, INVESTMEN, SAVINGS -> {
                this.balance+= amount;
            }
            case CREDIT -> {
                if (this.balance == 0.0 || this.balance < amount) {
                    throw new RuntimeException("Error en la transaccion");
                }
                this.balance-= amount;
            }
        }
    }

    private void expense(Double amount){
        switch (this.accountType){
            case CREDIT -> {
                this.balance+= amount;
            }
            case CHECKING, INVESTMEN, SAVINGS -> {
                if (this.balance == 0.0 || this.balance < amount) {
                    throw new RuntimeException("Error en la transaccion");
                }
                this.balance-= amount;
            }
        }
    }

    private void transfer(Double amount, Account destinationAccount){
        if (this.balance == 0.0 || this.balance < amount || destinationAccount == null ||
                destinationAccount.getId().equals(this.id)) {
            throw new RuntimeException("Error en la transaccion");
        }
        this.balance-=amount;
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);
    }

    public void revertir(Double amount) {
        this.balance-=amount;
    }
}

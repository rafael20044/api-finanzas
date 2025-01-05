package dev.rafaelbarragan.api.finanza.domain.account.entity;

import dev.rafaelbarragan.api.finanza.domain.account.dto.AccountCreate;
import dev.rafaelbarragan.api.finanza.domain.account.dto.AccountEdit;
import dev.rafaelbarragan.api.finanza.domain.enums.AccountType;
import dev.rafaelbarragan.api.finanza.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public Account(AccountCreate create, User user) {
        this.user = user;
        this.accountName = create.accountName();
        this.accountType = create.accountType();
        this.balance = 0.0;
    }

    public void update(AccountEdit edit){
        this.accountName = edit.accountName();
    }
}

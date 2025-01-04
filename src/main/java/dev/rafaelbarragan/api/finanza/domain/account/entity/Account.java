package dev.rafaelbarragan.api.finanza.domain.account.entity;

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

}

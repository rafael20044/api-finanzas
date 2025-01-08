package dev.rafaelbarragan.api.finanza.domain.user.dto;

import dev.rafaelbarragan.api.finanza.domain.account.entity.Account;
import dev.rafaelbarragan.api.finanza.domain.enums.AccountType;

public record UserAccount(

        Long id,

        String accountName,

        AccountType accountType,

        Double balance
) {
    public UserAccount(Account account){
        this(account.getId(), account.getAccountName(), account.getAccountType(), account.getBalance());
    }
}

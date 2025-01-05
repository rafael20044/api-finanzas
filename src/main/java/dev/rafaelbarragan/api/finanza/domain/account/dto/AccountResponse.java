package dev.rafaelbarragan.api.finanza.domain.account.dto;

import dev.rafaelbarragan.api.finanza.domain.account.entity.Account;
import dev.rafaelbarragan.api.finanza.domain.enums.AccountType;

public record AccountResponse(

        Long id,

        AccountUser user,

        String accountName,

        AccountType accountType,

        Double balance
) {
    public AccountResponse(Account account) {
        this(account.getId(), new AccountUser(account.getUser()),
                account.getAccountName(), account.getAccountType(), account.getBalance());
    }
}

package dev.rafaelbarragan.api.finanza.domain.transaction.dto;

import dev.rafaelbarragan.api.finanza.domain.account.dto.AccountUser;
import dev.rafaelbarragan.api.finanza.domain.account.entity.Account;
import dev.rafaelbarragan.api.finanza.domain.enums.AccountType;

public record TransactionAccount(

        Long id,

        AccountUser user,

        String accountName,

        AccountType accountType
) {
    public TransactionAccount(Account account) {
        this(account.getId(), new AccountUser(account.getUser()), account.getAccountName(), account.getAccountType());
    }
}

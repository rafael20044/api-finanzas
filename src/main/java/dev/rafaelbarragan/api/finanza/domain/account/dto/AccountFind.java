package dev.rafaelbarragan.api.finanza.domain.account.dto;

import dev.rafaelbarragan.api.finanza.domain.account.entity.Account;
import dev.rafaelbarragan.api.finanza.domain.enums.AccountType;

import java.util.List;

public record AccountFind(

        Long id,

        AccountUser user,

        String accountName,

        AccountType accountType,

        Double balance,

        List<AccountTransaction> transactiosList
) {
    public AccountFind(Account account) {
        this(account.getId(), new AccountUser(account.getUser()),
                account.getAccountName(), account.getAccountType(), account.getBalance(),
                account.getTransactions().stream().map(AccountTransaction::new).toList());
    }
}

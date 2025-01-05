package dev.rafaelbarragan.api.finanza.domain.account.dto;

import dev.rafaelbarragan.api.finanza.domain.enums.AccountType;

public record AccountCreate(

        Long user,

        String accountName,

        AccountType accountType

) {
}

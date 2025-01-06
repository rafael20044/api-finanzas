package dev.rafaelbarragan.api.finanza.domain.transaction.dto;

import dev.rafaelbarragan.api.finanza.domain.enums.TransactionType;
import jakarta.validation.constraints.NotNull;


public record TransactionCreate(

        @NotNull
        Long account,

        Long destinationAccount,

        @NotNull
        Double amount,

        @NotNull
        TransactionType transactionType,

        @NotNull
        String description

) {
}

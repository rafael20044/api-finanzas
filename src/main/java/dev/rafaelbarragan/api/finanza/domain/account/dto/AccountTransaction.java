package dev.rafaelbarragan.api.finanza.domain.account.dto;

import dev.rafaelbarragan.api.finanza.domain.enums.TransactionType;
import dev.rafaelbarragan.api.finanza.domain.transaction.entity.Transaction;

import java.time.LocalDateTime;

public record AccountTransaction(

        Long id,

        Double amount,

        TransactionType transactionType,

        String description,

        LocalDateTime transactionDate,

        LocalDateTime createdAt

) {

    public AccountTransaction(Transaction transaction){
        this(transaction.getId(), transaction.getAmount(), transaction.getTransactionType(), transaction.getDescription(),
                transaction.getTransactionDate(), transaction.getCreatedAt());
    }
}

package dev.rafaelbarragan.api.finanza.domain.transaction.dto;

import dev.rafaelbarragan.api.finanza.domain.enums.TransactionType;
import dev.rafaelbarragan.api.finanza.domain.transaction.entity.Transaction;

import java.time.LocalDateTime;

public record TransactionResponse(

        Long id,

        TransactionAccount account,

        TransactionAccount destinationAccount,

        Double amount,

        TransactionType transactionType,

        String description,

        LocalDateTime transactionDate,

        LocalDateTime createAt,

        Boolean cacelled
) {
    public TransactionResponse(Transaction transaction) {

        this(transaction.getId(), new TransactionAccount(transaction.getAccount()),
                transaction.getDestinationAccount() != null ?
                new TransactionAccount(transaction.getDestinationAccount()) : null, transaction.getAmount(),
                transaction.getTransactionType(), transaction.getDescription(), transaction.getTransactionDate(),
                transaction.getCreatedAt(),transaction.getCancelled());
    }
}

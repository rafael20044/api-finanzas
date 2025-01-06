package dev.rafaelbarragan.api.finanza.domain.transaction.service;

import dev.rafaelbarragan.api.finanza.domain.transaction.dto.TransactionCreate;
import dev.rafaelbarragan.api.finanza.domain.transaction.dto.TransactionResponse;

public interface ITransactionService {

    TransactionResponse crear(TransactionCreate create);

    TransactionResponse cancelar(Long id);
}

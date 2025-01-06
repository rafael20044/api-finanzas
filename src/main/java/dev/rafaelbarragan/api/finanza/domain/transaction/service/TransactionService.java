package dev.rafaelbarragan.api.finanza.domain.transaction.service;

import dev.rafaelbarragan.api.finanza.domain.account.entity.Account;
import dev.rafaelbarragan.api.finanza.domain.account.service.AccountService;
import dev.rafaelbarragan.api.finanza.domain.transaction.dto.TransactionCreate;
import dev.rafaelbarragan.api.finanza.domain.transaction.dto.TransactionResponse;
import dev.rafaelbarragan.api.finanza.domain.transaction.entity.Transaction;
import dev.rafaelbarragan.api.finanza.domain.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService implements ITransactionService{

    private final TransactionRepository repository;
    private final AccountService accountService;

    @Autowired
    public TransactionService(TransactionRepository repository, AccountService accountService){
        this.repository = repository;
        this.accountService = accountService;
    }

    @Override
    public TransactionResponse crear(TransactionCreate create) {
        Account account = accountService.findEntity(create.account());
        Account destinationAccount = create.destinationAccount() != null ?
                accountService.findEntity(create.destinationAccount()) : null;
        Transaction transaction = new Transaction(create, account, destinationAccount);
        account.transaction(transaction.getTransactionType(), transaction.getAmount(), destinationAccount);
        accountService.save(account);
        if (destinationAccount != null) {
            accountService.save(destinationAccount);
        }
        repository.save(transaction);
        return new TransactionResponse(transaction);
    }

    @Override
    public TransactionResponse cancelar(Long id) {
        Transaction transaction = repository.findById(id).orElseThrow(()-> new RuntimeException("Transaccion no encontrada"));
        if (LocalDateTime.now().isAfter(transaction.getCreatedAt().plusDays(2))){
            throw new RuntimeException("Tiempo para poder cancelar vencido");
        }
        if (transaction.getCancelled()) {
            throw new RuntimeException("Esta transaccion ya esta canselada");
        }
        Account account = transaction.getAccount();
        account.revertir(transaction.getAmount());
        transaction.cancelar();
        repository.save(transaction);
        accountService.save(account);
        return new TransactionResponse(transaction);
    }
}

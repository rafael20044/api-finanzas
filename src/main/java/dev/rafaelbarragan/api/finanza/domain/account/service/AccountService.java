package dev.rafaelbarragan.api.finanza.domain.account.service;

import dev.rafaelbarragan.api.finanza.domain.account.dto.AccountCreate;
import dev.rafaelbarragan.api.finanza.domain.account.dto.AccountEdit;
import dev.rafaelbarragan.api.finanza.domain.account.dto.AccountFind;
import dev.rafaelbarragan.api.finanza.domain.account.dto.AccountResponse;
import dev.rafaelbarragan.api.finanza.domain.account.entity.Account;
import dev.rafaelbarragan.api.finanza.domain.account.repository.AccountRepository;
import dev.rafaelbarragan.api.finanza.domain.user.entity.User;
import dev.rafaelbarragan.api.finanza.domain.user.service.UserService;
import dev.rafaelbarragan.api.finanza.exception.ExistenciaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class AccountService implements IAccountService{

    private final AccountRepository repository;
    private final UserService userService;

    @Autowired
    public AccountService(AccountRepository repository, UserService userService){
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public AccountResponse crear(AccountCreate create) {
        User user = userService.findEntity(create.user());
        Account account = new Account(create, user);
        repository.save(account);
        return new AccountResponse(account);
    }

    @Override
    public AccountFind find(Long id) {
        Account account = findEntity(id);
        return new AccountFind(account);
    }

    @Override
    public Page<AccountResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(AccountResponse::new);
    }

    @Override
    public AccountResponse edit(AccountEdit edit) {
        Account account = findEntity(edit.id());
        account.update(edit);
        repository.save(account);
        return new AccountResponse(account);
    }

    @Override
    public Account findEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> new ExistenciaException("Cuenta no exitente"));
    }

    @Override
    public String delete(Long id) {
        repository.deleteById(id);
        return "Usuario eliminado";
    }

    @Override
    public void save(Account account) {
        repository.save(account);
    }
}

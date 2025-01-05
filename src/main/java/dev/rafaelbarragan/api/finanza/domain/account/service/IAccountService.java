package dev.rafaelbarragan.api.finanza.domain.account.service;

import dev.rafaelbarragan.api.finanza.domain.account.dto.AccountCreate;
import dev.rafaelbarragan.api.finanza.domain.account.dto.AccountEdit;
import dev.rafaelbarragan.api.finanza.domain.account.dto.AccountResponse;
import dev.rafaelbarragan.api.finanza.domain.account.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAccountService {

    AccountResponse crear(AccountCreate create);

    AccountResponse find(Long id);

    Page<AccountResponse> findAll(Pageable pageable);

    AccountResponse edit(AccountEdit edit);

    Account findEntity(Long id);

    String delete(Long id);
}

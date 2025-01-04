package dev.rafaelbarragan.api.finanza.domain.account.repository;

import dev.rafaelbarragan.api.finanza.domain.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

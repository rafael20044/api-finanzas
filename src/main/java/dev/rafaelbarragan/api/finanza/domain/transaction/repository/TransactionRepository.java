package dev.rafaelbarragan.api.finanza.domain.transaction.repository;

import dev.rafaelbarragan.api.finanza.domain.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

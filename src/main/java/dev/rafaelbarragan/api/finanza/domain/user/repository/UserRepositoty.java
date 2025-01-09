package dev.rafaelbarragan.api.finanza.domain.user.repository;

import dev.rafaelbarragan.api.finanza.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoty  extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<UserDetails> findByUserName(String username);
}

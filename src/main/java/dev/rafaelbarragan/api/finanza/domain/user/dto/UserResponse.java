package dev.rafaelbarragan.api.finanza.domain.user.dto;

import dev.rafaelbarragan.api.finanza.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public record UserResponse(

        Long id,

        String userName,

        String email,

        LocalDateTime createdAt,

        List<UserAccount> accounts
) {
    public UserResponse(User user) {
        this(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt(),
                user.getAccountList().stream().map(UserAccount::new).toList());
    }
}

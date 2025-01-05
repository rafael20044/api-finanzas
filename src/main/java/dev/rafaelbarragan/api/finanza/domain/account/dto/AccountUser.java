package dev.rafaelbarragan.api.finanza.domain.account.dto;

import dev.rafaelbarragan.api.finanza.domain.user.entity.User;

public record AccountUser(
        Long id,
        String name
) {
    public AccountUser(User user) {
        this(user.getId(), user.getUserName());
    }
}

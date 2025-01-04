package dev.rafaelbarragan.api.finanza.domain.user.dto;

import dev.rafaelbarragan.api.finanza.domain.user.entity.User;

import java.time.LocalDateTime;

public record UserResponse(

        Long id,

        String userName,

        String email,

        LocalDateTime createdAt
) {
    public UserResponse(User user) {
        this(user.getId(), user.getUserName(), user.getEmail(), user.getCreatedAt());
    }
}

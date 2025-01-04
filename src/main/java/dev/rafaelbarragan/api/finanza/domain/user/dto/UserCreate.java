package dev.rafaelbarragan.api.finanza.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserCreate(

        @NotNull
        String userName,

        @NotNull
        @Email
        String email,

        @NotNull
        String password

) {
}

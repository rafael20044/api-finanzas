package dev.rafaelbarragan.api.finanza.domain.user.dto;

import jakarta.validation.constraints.NotNull;

public record UserAutenticar(

        @NotNull
        String userName,
        @NotNull
        String clave

) {
}

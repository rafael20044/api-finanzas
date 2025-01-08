package dev.rafaelbarragan.api.finanza.domain.user.dto;

import jakarta.validation.constraints.NotNull;

public record UserEdit (

        @NotNull
        Long id,

        String userName,

        String email,

        String password

){
}

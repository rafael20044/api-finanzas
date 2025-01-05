package dev.rafaelbarragan.api.finanza.domain.account.dto;

import jakarta.validation.constraints.NotNull;

public record AccountEdit(

        @NotNull
        Long id,

        String accountName

) {
}

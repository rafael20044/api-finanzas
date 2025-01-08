package dev.rafaelbarragan.api.finanza.exception;

public record Exception(
        String mensaje,
        Integer status
) {
}

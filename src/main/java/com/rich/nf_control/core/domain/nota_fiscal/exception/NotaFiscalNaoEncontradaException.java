package com.rich.nf_control.core.domain.nota_fiscal.exception;

import java.util.UUID;

public class NotaFiscalNaoEncontradaException extends RuntimeException {
    public NotaFiscalNaoEncontradaException(UUID id) {
        super("Nota fiscal com id: %s não encontrada".formatted(id.toString()));
    }
}
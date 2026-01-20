package com.rich.nf_control.core.helper;

import com.rich.nf_control.core.application.nota_fiscal.command.SalvarNotaFiscalCommand;
import com.rich.nf_control.core.domain.nota_fiscal.enums.NfStatus;
import com.rich.nf_control.core.domain.nota_fiscal.enums.Tipo;

import java.time.LocalDateTime;
import java.util.UUID;

public class ObjectsMocks {

    public static SalvarNotaFiscalCommand mockSalvarNotaFiscalCommand() {
        return new SalvarNotaFiscalCommand(
                "nfe212",
                "10",
                Tipo.RECEBIDA,
                NfStatus.PENDENTE,
                "emissor",
                "receptor",
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                1500.00,
                0.0,
                UUID.randomUUID()
        );
    }
}
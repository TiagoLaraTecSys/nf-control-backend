package com.rich.nf_control.core.helper;

import com.rich.nf_control.core.application.nota_fiscal.command.SalvarNotaFiscalCommand;
import com.rich.nf_control.core.domain.nota_fiscal.enums.NfStatus;
import com.rich.nf_control.core.domain.nota_fiscal.enums.Tipo;
import com.rich.nf_control.core.domain.nota_fiscal.model.NotaFiscal;

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

    public static NotaFiscal mockNotaFiscal() {
        NotaFiscal notaFiscal = new NotaFiscal();

        notaFiscal.setId(UUID.fromString("2f4d9a7c-9c3a-4d6a-b3f2-8f3c2a1a9b11"));
        notaFiscal.setCodigoVerificacao("COD-987654");
        notaFiscal.setNumero("NF-2026-00045");
        notaFiscal.setTipo(Tipo.RECEBIDA);
        notaFiscal.setStatus(NfStatus.PAGA);

        notaFiscal.setEmissor("Construtora Alpha LTDA");
        notaFiscal.setReceptor("Rich Engenharia LTDA");

        notaFiscal.setDataEmissao(LocalDateTime.of(2026, 1, 10, 10, 30));
        notaFiscal.setDataVencimento(LocalDateTime.of(2026, 2, 10, 23, 59));
        notaFiscal.setDataPagamento(LocalDateTime.of(2026, 2, 5, 14, 20));

        notaFiscal.setValorEmitido(18500.00);
        notaFiscal.setValorPago(18500.00);

        notaFiscal.setObraId(UUID.fromString("b2b7b9a4-0c12-4c7b-bf71-8c6d9a0a55aa"));
        return notaFiscal;
    }
}
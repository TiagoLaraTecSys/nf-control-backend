package com.rich.nf_control.core.domain.nota_fiscal.model;

import com.rich.nf_control.core.application.nota_fiscal.command.SalvarNotaFiscalCommand;
import com.rich.nf_control.core.domain.nota_fiscal.enums.NfStatus;
import com.rich.nf_control.core.domain.nota_fiscal.enums.Tipo;
import com.rich.nf_control.core.domain.nota_fiscal.exception.ObraIdNaoInformadaException;
import com.rich.nf_control.core.domain.nota_fiscal.exception.TipoNotaFiscalObrigatorioException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotaFiscalTest {

    @Test
    void deve_lancar_excecao_caso_tipo_seja_nullo() {

        SalvarNotaFiscalCommand command = mockCommand();
        command.setTipo(null);
        assertThrows( TipoNotaFiscalObrigatorioException.class,() -> {
            NotaFiscal nova = NotaFiscal.criar(command);
        });
    }

    @Test
    void status_deve_ser_pendente_se_vier_null() {

        SalvarNotaFiscalCommand command = mockCommand();
        command.setStatus(null);

        NotaFiscal nf = NotaFiscal.criar(command);

        assertEquals(NfStatus.PENDENTE, nf.getStatus());
    }

    @Test
    void deve_lancar_excecao_caso_obra_id_seja_nullo() {

        SalvarNotaFiscalCommand command = mockCommand();
        command.setObraId(null);
        assertThrows( ObraIdNaoInformadaException.class,() -> {
            NotaFiscal nova = NotaFiscal.criar(command);
        });
    }

    @Test
    void deve_criar_nota_fiscal_a_partir_do_command() {

        SalvarNotaFiscalCommand command = mockCommand();
        NotaFiscal nf = NotaFiscal.criar(command);

        assertEquals(command.getObraId(), nf.getObraId());
        assertEquals(command.getTipo(), nf.getTipo());
        assertEquals(command.getStatus(), nf.getStatus());
        assertEquals(command.getCodigoVerificacao(), nf.getCodigoVerificacao());
        assertEquals(command.getReceptor(), nf.getReceptor());
        assertEquals(command.getEmissor(), nf.getEmissor());
        assertEquals(command.getDataEmissao(), nf.getDataEmissao());
        assertEquals(command.getDataVencimento(), nf.getDataVencimento());
        assertEquals(command.getDataPagamento(), nf.getDataPagamento());
        assertEquals(command.getValorEmitido(), nf.getValorEmitido());
        assertEquals(command.getValorPago(), nf.getValorPago());
    }

    private SalvarNotaFiscalCommand mockCommand() {
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
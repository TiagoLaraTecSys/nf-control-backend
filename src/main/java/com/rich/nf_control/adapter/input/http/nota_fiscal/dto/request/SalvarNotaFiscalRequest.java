package com.rich.nf_control.adapter.input.http.nota_fiscal.dto.request;

import com.rich.nf_control.core.application.nota_fiscal.command.SalvarNotaFiscalCommand;
import com.rich.nf_control.core.domain.nota_fiscal.enums.NfStatus;
import com.rich.nf_control.core.domain.nota_fiscal.enums.Tipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record SalvarNotaFiscalRequest(

        @NotBlank
        String codigoVerificacao,

        @NotBlank
        String numero,

        @NotNull
        Tipo tipo,

        NfStatus status,

        @NotBlank
        String emissor,

        @NotBlank
        String receptor,

        @NotNull
        LocalDateTime dataEmissao,

        @NotNull
        LocalDateTime dataVencimento,

        LocalDateTime dataPagamento,

        @NotNull
        Double valorEmitido,

        Double valorPago,

        UUID obraId
) {

    public SalvarNotaFiscalCommand toCommand() {
        return new SalvarNotaFiscalCommand(
                codigoVerificacao,
                numero,
                tipo,
                status,
                emissor,
                receptor,
                dataEmissao,
                dataVencimento,
                dataPagamento,
                valorEmitido,
                valorPago,
                obraId
        );
    }
}
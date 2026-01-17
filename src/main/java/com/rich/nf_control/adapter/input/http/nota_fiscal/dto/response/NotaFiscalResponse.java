package com.rich.nf_control.adapter.input.http.nota_fiscal.dto.response;

import com.rich.nf_control.core.domain.nota_fiscal.enums.NfStatus;
import com.rich.nf_control.core.domain.nota_fiscal.enums.Tipo;
import com.rich.nf_control.core.domain.nota_fiscal.model.NotaFiscal;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotaFiscalResponse(
        UUID id,
        String codigoVerificacao,
        String numero,
        Tipo tipo,
        NfStatus status,
        String emissor,
        String receptor,
        LocalDateTime dataEmissao,
        LocalDateTime dataVencimento,
        LocalDateTime dataPagamento,
        Double valorEmitido,
        Double valorPago,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UUID obraId
) {

    public static NotaFiscalResponse from(NotaFiscal notaFiscal) {
        return new NotaFiscalResponse(
                notaFiscal.getId(),
                notaFiscal.getCodigoVerificacao(),
                notaFiscal.getNumero(),
                notaFiscal.getTipo(),
                notaFiscal.getStatus(),
                notaFiscal.getEmissor(),
                notaFiscal.getReceptor(),
                notaFiscal.getDataEmissao(),
                notaFiscal.getDataVencimento(),
                notaFiscal.getDataPagamento(),
                notaFiscal.getValorEmitido(),
                notaFiscal.getValorPago(),
                notaFiscal.getCreatedAt(),
                notaFiscal.getUpdatedAt(),
                notaFiscal.getObraId()
        );
    }
}
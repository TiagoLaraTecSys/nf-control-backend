package com.rich.nf_control.core.application.nota_fiscal.command;

import com.rich.nf_control.core.domain.nota_fiscal.enums.NfStatus;
import com.rich.nf_control.core.domain.nota_fiscal.enums.Tipo;
import com.rich.nf_control.core.domain.nota_fiscal.model.NotaFiscal;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class SalvarNotaFiscalCommand {

    private String codigoVerificacao;
    private String numero;
    private Tipo tipo;
    private NfStatus status;
    private String emissor;
    private String receptor;
    private LocalDateTime dataEmissao;
    private LocalDateTime dataVencimento;
    private LocalDateTime dataPagamento;
    private Double valorEmitido;
    private Double valorPago;
    private UUID obraId;

}
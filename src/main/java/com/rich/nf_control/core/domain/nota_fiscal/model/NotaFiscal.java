package com.rich.nf_control.core.domain.nota_fiscal.model;

import com.rich.nf_control.core.application.nota_fiscal.command.SalvarNotaFiscalCommand;
import com.rich.nf_control.core.domain.nota_fiscal.enums.NfStatus;
import com.rich.nf_control.core.domain.nota_fiscal.enums.Tipo;
import com.rich.nf_control.core.domain.nota_fiscal.exception.ObraIdNaoInformadaException;
import com.rich.nf_control.core.domain.nota_fiscal.exception.TipoNotaFiscalObrigatorioException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotaFiscal {

    private UUID id;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UUID obraId;

    public static NotaFiscal criar(SalvarNotaFiscalCommand command) {

        if (command.getTipo() == null) {
            throw new TipoNotaFiscalObrigatorioException("Tipo da nota RECEBIDA | EMITIDA nao informado");
        }

        if(command.getObraId() == null) {
            throw new ObraIdNaoInformadaException("Obra id nao informado, nao será possível atrelar essa nota a uma obra");
        }

        NotaFiscal nf = new NotaFiscal();
        nf.obraId = command.getObraId();
        nf.codigoVerificacao = command.getCodigoVerificacao();
        nf.numero = command.getNumero();
        nf.emissor = command.getEmissor();
        nf.receptor = command.getReceptor();
        nf.dataEmissao = command.getDataEmissao();
        nf.dataVencimento = command.getDataVencimento();
        nf.dataPagamento = command.getDataPagamento();
        nf.valorEmitido = command.getValorEmitido();
        nf.valorPago = command.getValorPago();
        nf.tipo = command.getTipo();

        nf.status = command.getStatus() != null
                ? command.getStatus()
                : NfStatus.PENDENTE;

        return nf;
    }
}
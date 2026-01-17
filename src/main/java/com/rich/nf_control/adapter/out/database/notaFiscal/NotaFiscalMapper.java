package com.rich.nf_control.adapter.out.database.notaFiscal;

import com.rich.nf_control.core.domain.nota_fiscal.model.NotaFiscal;

public class NotaFiscalMapper {

    public static NotaFiscalEntity toEntity(NotaFiscal nf) {
        NotaFiscalEntity e = new NotaFiscalEntity();

        e.setId(nf.getId());
        e.setCodigoVerificacao(nf.getCodigoVerificacao());
        e.setNumero(nf.getNumero());
        e.setTipo(nf.getTipo());
        e.setStatus(nf.getStatus());
        e.setEmissor(nf.getEmissor());
        e.setReceptor(nf.getReceptor());
        e.setDataEmissao(nf.getDataEmissao());
        e.setDataVencimento(nf.getDataVencimento());
        e.setDataPagamento(nf.getDataPagamento());
        e.setValorEmitido(nf.getValorEmitido());
        e.setValorPago(nf.getValorPago());
        e.setCreatedAt(nf.getCreatedAt());
        e.setUpdatedAt(nf.getUpdatedAt());
        e.setObraId(nf.getObraId());

        return e;
    }

    public static NotaFiscal toDomain(NotaFiscalEntity e) {
        NotaFiscal nf = new NotaFiscal();

        nf.setId(e.getId());
        nf.setCodigoVerificacao(e.getCodigoVerificacao());
        nf.setNumero(e.getNumero());
        nf.setTipo(e.getTipo());
        nf.setStatus(e.getStatus());
        nf.setEmissor(e.getEmissor());
        nf.setReceptor(e.getReceptor());
        nf.setDataEmissao(e.getDataEmissao());
        nf.setDataVencimento(e.getDataVencimento());
        nf.setDataPagamento(e.getDataPagamento());
        nf.setValorEmitido(e.getValorEmitido());
        nf.setValorPago(e.getValorPago());
        nf.setCreatedAt(e.getCreatedAt());
        nf.setUpdatedAt(e.getUpdatedAt());
        nf.setObraId(e.getObraId());

        return nf;
    }

}
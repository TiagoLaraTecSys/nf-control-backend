package com.rich.nf_control.adapter.out.database.notaFiscal;

import com.rich.nf_control.core.domain.nota_fiscal.enums.NfStatus;
import com.rich.nf_control.core.domain.nota_fiscal.enums.Tipo;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "nota_fiscal")
@Data
public class NotaFiscalEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "codigo_verificacao", nullable = false, unique = true)
    private String codigoVerificacao;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private Tipo tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private NfStatus status;

    @Column(name = "emissor", nullable = false)
    private String emissor;

    @Column(name = "receptor", nullable = false)
    private String receptor;

    @Column(name = "data_emissao", nullable = false)
    private LocalDateTime dataEmissao;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDateTime dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    @Column(name = "valor_emitido", nullable = false)
    private Double valorEmitido;

    @Column(name = "valor_pago")
    private Double valorPago;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "obra_id", nullable = false)
    private UUID obraId;
}
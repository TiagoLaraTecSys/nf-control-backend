package com.rich.nf_control.adapter.in.http.nota_fiscal.controller;


import com.rich.nf_control.adapter.input.http.nota_fiscal.controller.NotaFiscalController;
import com.rich.nf_control.adapter.input.http.nota_fiscal.dto.request.SalvarNotaFiscalRequest;
import com.rich.nf_control.core.application.nota_fiscal.usecase.NotaFiscalUseCase;
import com.rich.nf_control.core.domain.nota_fiscal.enums.NfStatus;
import com.rich.nf_control.core.domain.nota_fiscal.enums.Tipo;
import com.rich.nf_control.core.helper.ObjectsMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class NotaFiscalControllerTest {

    @Mock
    private NotaFiscalUseCase useCase;

    @InjectMocks
    private NotaFiscalController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deve_salvar_nova_nf() {
        SalvarNotaFiscalRequest request = new SalvarNotaFiscalRequest(
                "COD-123456",                 // codigoVerificacao
                "NF-2026-0001",                // numero
                Tipo.RECEBIDA,                 // tipo
                NfStatus.PENDENTE,             // status
                "Construtora Alpha LTDA",      // emissor
                "Rich Engenharia LTDA",        // receptor
                LocalDateTime.now(),           // dataEmissao
                LocalDateTime.now().plusDays(30), // dataVencimento
                null,                          // dataPagamento
                15000.00,                      // valorEmitido
                null,                          // valorPago
                UUID.randomUUID()              // obraId
        );

        when(useCase.save(request.toCommand())).thenReturn(ObjectsMocks.mockNotaFiscal());
        ResponseEntity response = controller.salvarNotaFiscal(request);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }
}
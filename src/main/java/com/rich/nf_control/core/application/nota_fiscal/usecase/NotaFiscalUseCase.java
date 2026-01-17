package com.rich.nf_control.core.application.nota_fiscal.usecase;

import com.rich.nf_control.core.application.nota_fiscal.command.SalvarNotaFiscalCommand;
import com.rich.nf_control.core.application.nota_fiscal.out.NotaFiscalRepository;
import com.rich.nf_control.core.domain.nota_fiscal.enums.NfStatus;
import com.rich.nf_control.core.domain.nota_fiscal.exception.NotaFiscalNaoEncontradaException;
import com.rich.nf_control.core.domain.nota_fiscal.model.NotaFiscal;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class NotaFiscalUseCase {

    private final NotaFiscalRepository notaFiscalRepository;

    public NotaFiscalUseCase(NotaFiscalRepository notaFiscalRepository) {
        this.notaFiscalRepository = notaFiscalRepository;
    }

    public NotaFiscal save(SalvarNotaFiscalCommand input) {
        NotaFiscal novaNotaFiscal = NotaFiscal.criar(input);

        novaNotaFiscal.setId(UUID.randomUUID());
        novaNotaFiscal.setCreatedAt(LocalDateTime.now());
        novaNotaFiscal.setUpdatedAt(LocalDateTime.now());

        return this.notaFiscalRepository.save(novaNotaFiscal);
    }

    public NotaFiscal findById(UUID id) {

        return this.notaFiscalRepository.findById(id)
                .orElseThrow(() -> new NotaFiscalNaoEncontradaException(id));
    }
}
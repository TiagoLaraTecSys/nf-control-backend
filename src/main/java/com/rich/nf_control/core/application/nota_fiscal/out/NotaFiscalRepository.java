package com.rich.nf_control.core.application.nota_fiscal.out;

import com.rich.nf_control.core.application.shared.pagination.PageResult;
import com.rich.nf_control.core.domain.nota_fiscal.model.NotaFiscal;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotaFiscalRepository {

    NotaFiscal save(NotaFiscal notaFiscal);
    Optional<NotaFiscal> findById(UUID id);
    List<NotaFiscal> listAllNotaFiscal();
    PageResult<NotaFiscal> listPaginated(int page, int size);
}
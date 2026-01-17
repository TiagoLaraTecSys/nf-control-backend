package com.rich.nf_control.adapter.out.database.notaFiscal;

import com.rich.nf_control.core.application.nota_fiscal.out.NotaFiscalRepository;
import com.rich.nf_control.core.domain.nota_fiscal.model.NotaFiscal;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class NotaFiscalRepositoryAdapter implements NotaFiscalRepository {

    private final NotaFiscalJpaRepository notaFiscalJpaRepository;

    public NotaFiscalRepositoryAdapter(NotaFiscalJpaRepository notaFiscalJpaRepository) {
        this.notaFiscalJpaRepository = notaFiscalJpaRepository;
    }

    @Override
    public NotaFiscal save(NotaFiscal notaFiscal) {
        NotaFiscalEntity entity = NotaFiscalMapper.toEntity(notaFiscal);
        NotaFiscalEntity model = this.notaFiscalJpaRepository.save(entity);
        return NotaFiscalMapper.toDomain(model);
    }

    @Override
    public Optional<NotaFiscal> findById(UUID id) {

        Optional<NotaFiscalEntity> nf = this.notaFiscalJpaRepository.findById(id);
        return nf.map(NotaFiscalMapper::toDomain);
    }
}
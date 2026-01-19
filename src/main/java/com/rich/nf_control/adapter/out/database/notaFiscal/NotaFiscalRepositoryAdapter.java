package com.rich.nf_control.adapter.out.database.notaFiscal;

import com.rich.nf_control.core.application.nota_fiscal.out.NotaFiscalRepository;
import com.rich.nf_control.core.application.shared.pagination.PageResult;
import com.rich.nf_control.core.domain.nota_fiscal.model.NotaFiscal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
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

    @Override
    public List<NotaFiscal> listAllNotaFiscal() {
        List<NotaFiscalEntity> nfEntity = this.notaFiscalJpaRepository.findAll();
        return nfEntity.stream().map(NotaFiscalMapper::toDomain).toList();
    }

    @Override
    public PageResult<NotaFiscal> listPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<NotaFiscalEntity> result =
                notaFiscalJpaRepository.findAll(pageable);

        List<NotaFiscal> items = result.getContent()
                .stream()
                .map(NotaFiscalMapper::toDomain)
                .toList();

        return new PageResult<>(
                items,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }
}
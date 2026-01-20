package com.rich.nf_control.adapter.out.database.notaFiscal;

import com.rich.nf_control.core.application.shared.pagination.PageResult;
import com.rich.nf_control.core.domain.nota_fiscal.model.NotaFiscal;
import com.rich.nf_control.core.helper.ObjectsMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;
import static org.mockito.Mockito.*;

public class NotaFiscalRepositoryAdapterTest {

    @Mock
    private NotaFiscalJpaRepository jpaRepository;

    @InjectMocks
    private NotaFiscalRepositoryAdapter adapter;

    @BeforeEach
    void seup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deve_salvar_nf_na_base() {
        NotaFiscal notaFiscal = NotaFiscal.criar(ObjectsMocks.mockSalvarNotaFiscalCommand());
        notaFiscal.setId(UUID.randomUUID());

        NotaFiscalEntity entitySalva = NotaFiscalMapper.toEntity(notaFiscal);

        when(jpaRepository.save(any(NotaFiscalEntity.class)))
                .thenReturn(entitySalva);

        // act
        NotaFiscal result = adapter.save(notaFiscal);

        // assert
        assertNotNull(result);
        assertEquals(notaFiscal.getId(), result.getId());

        ArgumentCaptor<NotaFiscalEntity> captor =
                ArgumentCaptor.forClass(NotaFiscalEntity.class);

        verify(jpaRepository).save(captor.capture());

        NotaFiscalEntity entityCapturada = captor.getValue();

        assertEquals(notaFiscal.getId(), entityCapturada.getId());
    }

    @Test
    void deve_buscar_nota_fiscal_por_id_quando_existir() {
        UUID id = UUID.randomUUID();

        NotaFiscalEntity entity = new NotaFiscalEntity();
        entity.setId(id);

        when(jpaRepository.findById(id))
                .thenReturn(Optional.of(entity));

        Optional<NotaFiscal> result = adapter.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());

        verify(jpaRepository).findById(id);
    }

    @Test
    void deve_retornar_optional_vazio_quando_nota_fiscal_nao_existir() {
        UUID id = UUID.randomUUID();

        when(jpaRepository.findById(id))
                .thenReturn(Optional.empty());

        Optional<NotaFiscal> result = adapter.findById(id);

        assertTrue(result.isEmpty());

        verify(jpaRepository).findById(id);
    }

    @Test
    void deve_listar_todas_as_notas_fiscais() {
        NotaFiscalEntity nf1 = new NotaFiscalEntity();
        nf1.setId(UUID.randomUUID());

        NotaFiscalEntity nf2 = new NotaFiscalEntity();
        nf2.setId(UUID.randomUUID());

        when(jpaRepository.findAll())
                .thenReturn(List.of(nf1, nf2));

        List<NotaFiscal> result = adapter.listAllNotaFiscal();

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals(nf1.getId(), result.get(0).getId());
        assertEquals(nf2.getId(), result.get(1).getId());

        verify(jpaRepository).findAll();
    }

    @Test
    void deve_listar_notas_fiscais_paginadas() {
        int page = 0;
        int size = 10;

        NotaFiscalEntity nf1 = new NotaFiscalEntity();
        nf1.setId(UUID.randomUUID());

        NotaFiscalEntity nf2 = new NotaFiscalEntity();
        nf2.setId(UUID.randomUUID());

        Page<NotaFiscalEntity> pageResult =
                new PageImpl<>(
                        List.of(nf1, nf2),
                        PageRequest.of(page, size),
                        2
                );

        when(jpaRepository.findAll(any(Pageable.class)))
                .thenReturn(pageResult);

        PageResult<NotaFiscal> result =
                adapter.listPaginated(page, size);

        assertNotNull(result);
        assertEquals(2, result.getItems().size());
        assertEquals(page, result.getPage());
        assertEquals(size, result.getSize());
        assertEquals(2, result.getTotalElements());
        assertEquals(1, result.getTotalPages());

        assertEquals(nf1.getId(), result.getItems().get(0).getId());
        assertEquals(nf2.getId(), result.getItems().get(1).getId());

        verify(jpaRepository).findAll(any(Pageable.class));
    }

}
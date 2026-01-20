package com.rich.nf_control.core.application.nota_fiscal.usecase;

import com.rich.nf_control.core.application.nota_fiscal.command.SalvarNotaFiscalCommand;
import com.rich.nf_control.core.application.nota_fiscal.out.NotaFiscalRepository;
import com.rich.nf_control.core.application.shared.pagination.PageResult;
import com.rich.nf_control.core.domain.nota_fiscal.exception.NotaFiscalNaoEncontradaException;
import com.rich.nf_control.core.domain.nota_fiscal.model.NotaFiscal;
import com.rich.nf_control.core.helper.ObjectsMocks;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NotaFiscalUseCaseTest {

    @Mock
    private NotaFiscalRepository repository;

    @InjectMocks
    private NotaFiscalUseCase useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deve_salvar_nf_na_base() {

        SalvarNotaFiscalCommand command = ObjectsMocks.mockSalvarNotaFiscalCommand();

        useCase.save(command);
        ArgumentCaptor<NotaFiscal> argumentCaptor = ArgumentCaptor.forClass(NotaFiscal.class);
        verify(repository, atLeastOnce()).save(argumentCaptor.capture());

        assertNotNull(argumentCaptor.getValue().getId());
        assertEquals(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), argumentCaptor.getValue().getCreatedAt().truncatedTo(ChronoUnit.SECONDS));
        assertEquals(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), argumentCaptor.getValue().getUpdatedAt().truncatedTo(ChronoUnit.SECONDS));
    }

    @Test
    void deve_lancar_excecao_caso_nao_encontre_nota_fiscal() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        NotaFiscalNaoEncontradaException ex = assertThrows(NotaFiscalNaoEncontradaException.class,
                () -> useCase.findById(id));

        assertTrue(ex.getMessage().contains(id.toString()));

        verify(repository).findById(id);
    }

    @Test
    void deve_listar_nfs_paginadas(){
        int page = 0;
        int size = 10;

        PageResult<NotaFiscal> pageResult =
                new PageResult<>(List.of(new NotaFiscal()), 1, 10, 1, 1);

        when(repository.listPaginated(page, size))
                .thenReturn(pageResult);

        PageResult<NotaFiscal> result =
                useCase.listarNotasFiscaisPaginadas(page, size);

        assertNotNull(result);
        assertEquals(pageResult, result);

        verify(repository).listPaginated(page, size);


    }

    @Test
    void deve_listar_todas_nfes() {

        useCase.listarTodasNotasFiscais();

        verify(repository, atLeastOnce()).listAllNotaFiscal();
    }
}
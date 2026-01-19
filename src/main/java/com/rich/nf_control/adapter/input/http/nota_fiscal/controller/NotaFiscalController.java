package com.rich.nf_control.adapter.input.http.nota_fiscal.controller;

import com.rich.nf_control.adapter.input.http.nota_fiscal.dto.request.SalvarNotaFiscalRequest;
import com.rich.nf_control.adapter.input.http.nota_fiscal.dto.response.NotaFiscalResponse;
import com.rich.nf_control.adapter.input.http.shared.PageResponse;
import com.rich.nf_control.core.application.nota_fiscal.usecase.NotaFiscalUseCase;
import com.rich.nf_control.core.application.shared.pagination.PageResult;
import com.rich.nf_control.core.domain.nota_fiscal.model.NotaFiscal;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/nota-fiscal")
public class NotaFiscalController {

    private final NotaFiscalUseCase useCase;

    public NotaFiscalController(NotaFiscalUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotaFiscalResponse> salvarNotaFiscal(@RequestBody @Valid SalvarNotaFiscalRequest request) {

        NotaFiscal nf = this.useCase.save(request.toCommand());
        return ResponseEntity.ok(NotaFiscalResponse.from(nf));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotaFiscalResponse> buscarPorId(@Valid @PathVariable UUID id) {

        NotaFiscal nf = this.useCase.findById(id);
        return ResponseEntity.ok(NotaFiscalResponse.from(nf));
    }

    @GetMapping(value = "/list-all")
    public ResponseEntity<List<NotaFiscal>> listAll() {
        List<NotaFiscal> nfs = this.useCase.listarTodasNotasFiscais();
        return ResponseEntity.ok(nfs);
    }

    @GetMapping(value = "/list-paginated")
    public ResponseEntity<PageResponse> listPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        PageResult<NotaFiscal> nfsPageable = this.useCase.listarNotasFiscaisPaginadas(page, size);
        return ResponseEntity.ok(
            PageResponse.from(nfsPageable, NotaFiscalResponse::from)
        );
    }
}
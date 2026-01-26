package com.rich.nf_control.adapter.input.http.nota_fiscal.controller;

import com.rich.nf_control.adapter.out.ai.OpenAiNotaFiscalExtractor;
import com.rich.nf_control.core.domain.nota_fiscal.model.NotaFiscal;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/nota-fiscal")
public class ExtractorNotaFiscalController {

    private final OpenAiNotaFiscalExtractor extractor;

    public ExtractorNotaFiscalController(OpenAiNotaFiscalExtractor extractor) {
        this.extractor = extractor;
    }

    @RequestMapping(value = "/extrair", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> extrair(
            @RequestPart("file")MultipartFile file
            ) {

        try {
            NotaFiscal nf = this.extractor.extrairDadosNota(file);
            return ResponseEntity.ok(nf);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
}
package com.rich.nf_control.core.application.nota_fiscal.out;

import com.rich.nf_control.core.domain.nota_fiscal.model.NotaFiscal;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface NotaFiscalFileExtractor {

    NotaFiscal extrairDadosNota(MultipartFile arquvo) throws IOException;
}
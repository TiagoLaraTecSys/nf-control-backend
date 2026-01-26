package com.rich.nf_control.adapter.out.ai;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openai.client.OpenAIClient;
import com.openai.core.JsonValue;
import com.openai.core.ObjectMappers;
import com.openai.models.ChatModel;
import com.openai.models.files.FileCreateParams;
import com.openai.models.files.FilePurpose;
import com.openai.models.graders.gradermodels.LabelModelGrader;
import com.openai.models.responses.*;
import com.openai.models.uploads.UploadCompleteParams;
import com.rich.nf_control.core.application.nota_fiscal.out.NotaFiscalFileExtractor;
import com.rich.nf_control.core.domain.nota_fiscal.model.NotaFiscal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
@Slf4j
public class OpenAiNotaFiscalExtractor implements NotaFiscalFileExtractor {

    private final OpenAIClient openAIClient;
    private final ObjectMapper objectMappers;

    public OpenAiNotaFiscalExtractor(OpenAIClient openAIClient, ObjectMapper objectMappers) {
        this.openAIClient = openAIClient;
        this.objectMappers = objectMappers;
    }

    @Override
    public NotaFiscal extrairDadosNota(MultipartFile arquvo) throws IOException {
        Path tempFile = Files.createTempFile(
                "nfe-",
                arquvo.getOriginalFilename()
        );
        arquvo.transferTo(tempFile);

        String fileId = openAIClient.files()
                .create(
                        FileCreateParams.builder()
                                .purpose(FilePurpose.ASSISTANTS)
                                .file(tempFile)
                                .build()
                ).id();

        ResponseInputText responseInputText = ResponseInputText
                .builder().text(prompt()).build();
        ResponseInputFile responseInputFile = ResponseInputFile.builder()
                .fileId(fileId)
                .build();
        var response = openAIClient.responses().create(ResponseCreateParams.builder()
                        .model(ChatModel.GPT_5_2)
                        .inputOfResponse(
                                List.of(
                                        ResponseInputItem.ofMessage(
                                                ResponseInputItem.Message.builder()
                                                        .addContent(responseInputFile)
                                                        .role(ResponseInputItem.Message.Role.USER)
                                                        .build()
                                        ),
                                        ResponseInputItem.ofMessage(
                                                ResponseInputItem.Message.builder()
                                                        .addContent(responseInputText)
                                                        .role(ResponseInputItem.Message.Role.SYSTEM)
                                                        .build())
                                )
                        )
                .build());
        String json = response.output().get(0).message().get().content().get(0).outputText().get().text();
        log.info("JSON extraido: {}", json);
        return objectMappers.readValue(json, NotaFiscal.class);
    }

    private String prompt() {
        return """
                Você é um sistema especialista em notas fiscais brasileiras.

                Analise a nota fiscal enviada e extraia os seguintes campos, você irá analisar todos os dados da NFE e interpreta-los para
                que possa preenche o json com os dados.

                Retorne SOMENTE um JSON válido. Este JSON abaixo como exemplo:
                {
                  "codigoVerificacao": string,
                  "numero": string,
                  "tipo": EMITIDA | RECEBIDA,
                  "status": "PENDENTE",
                  "emissor": string,
                  "receptor": string,
                  "dataEmissao": string,
                  "dataVencimento": string,
                  "dataPagamento": string,
                  "valorEmitido": number,
                  "valorPago": number,
                }
                
                """;
    }
}
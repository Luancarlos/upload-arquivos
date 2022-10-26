package br.com.ccee.adapter.http;

import br.com.ccee.core.exception.ApplicationException;
import br.com.ccee.core.port.in.ExtrairDadosArquivoPort;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public class UploadController {

    private final ExtrairDadosArquivoPort extrairDadosArquivoPort;

    public UploadController(ExtrairDadosArquivoPort extrairDadosArquivoPort) {
        this.extrairDadosArquivoPort = extrairDadosArquivoPort;
    }

    @PostMapping("/upload")
    @Operation(summary = "Upload de arquivo XML")
    public ResponseEntity<Void> uploadFile(@RequestParam (required = true) MultipartFile file) throws IOException {
        if (!file.getContentType().equals("text/xml")) {
            throw new ApplicationException("Formato do arquivo inválido");
        }
        extrairDadosArquivoPort.execute(file.getInputStream());
        return ResponseEntity.ok().build();
    }
}

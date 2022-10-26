package br.com.ccee.core.usecase;

import br.com.ccee.core.port.out.ApresentarInfoPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@ExtendWith(MockitoExtension.class)
public class ExtrairDadosArquivoUseCaseTest {

    @Mock
    private ApresentarInfoPort apresentarInfoPort;

    @InjectMocks
    private ExtrairDadosArquivoUseCase extrairDadosArquivoUseCase;


    @Test
    @DisplayName("Deve extrair os codigos dos agentes com sucesso")
    void deveExtrairOsCodigosDosAgentesComSucesso() throws FileNotFoundException {

        File file = ResourceUtils.getFile("classpath:example.xml");
        InputStream inputStream = new FileInputStream(file);

        Mockito.doNothing().when(apresentarInfoPort).execute(Mockito.anyList());

        extrairDadosArquivoUseCase.execute(inputStream);

        Mockito.verify(apresentarInfoPort, Mockito.times(1)).execute(Mockito.anyList());
    }
}

package br.com.ccee.core.usecase;

import br.com.ccee.core.exception.ApplicationException;
import br.com.ccee.core.port.in.ExtrairDadosArquivoPort;
import br.com.ccee.core.port.out.ApresentarInfoPort;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExtrairDadosArquivoUseCase implements ExtrairDadosArquivoPort {

    private final ApresentarInfoPort apresentarInfoPort;

    public ExtrairDadosArquivoUseCase(ApresentarInfoPort apresentarInfoPort) {
        this.apresentarInfoPort = apresentarInfoPort;
    }

    @Override
    public void execute(InputStream inputStream) {
        var codigos = this.extrairCodigoAgente(inputStream);
        apresentarInfoPort.execute(codigos);
    }

    private List<Long> extrairCodigoAgente(InputStream inputStream) {
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(inputStream);
            List<Node> nodes = document.selectNodes("/agentes/agente" );

            return nodes.stream()
                    .map(item -> Long.valueOf(item.selectSingleNode("codigo").getText()))
                    .collect(Collectors.toList());
        } catch (DocumentException e) {
            log.info("Ocorreu um erro ao manipular o XML {}", e.getMessage());
            throw new ApplicationException("Ocorreu um erro ao manipular XML");
        }
    }
}

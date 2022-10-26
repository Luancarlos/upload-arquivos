package br.com.ccee.adapter.datasource;

import br.com.ccee.core.port.out.ApresentarInfoPort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LogService implements ApresentarInfoPort {
    @Override
    public void execute(List<Long> codigos) {
        codigos.forEach(codigo -> System.out.println("CODIGO: " + codigo));
    }
}

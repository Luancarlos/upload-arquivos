package br.com.ccee.adapter.datasource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogServiceTest {

    private LogService logService = new LogService();

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Deve imprimir os codigos dos agentes com sucesso")
    void deveImprimirOsCodigoDosAgentesComSucesso() {
        var logs = Arrays.asList(1L, 2L, 3L);
        logService.execute(logs);

        assertEquals("CODIGO: 1\n" +
                "CODIGO: 2\n" +
                "CODIGO: 3", outputStreamCaptor.toString()
                .trim());
    }
}

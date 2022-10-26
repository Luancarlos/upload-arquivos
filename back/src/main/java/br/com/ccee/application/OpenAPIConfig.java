package br.com.ccee.application;

import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CCEE")
                        .description("API upload arquivo XML")
                        .contact(new Contact().name("Luan Rocha").email("luanbam@hotmail.com"))
                        .version("1.0.0"));
    }
}

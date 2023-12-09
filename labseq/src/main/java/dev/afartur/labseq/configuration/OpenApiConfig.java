package dev.afartur.labseq.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * Class for OpenAPi configuration.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Returns custom OpenAPI object for LabSeq API.
     * @return OpenAPI custom object.
     */
    public OpenAPI LabSeqOpenApi() {
        Contact contact = new Contact();
        contact.setEmail("arturcferra2002@gmail.com");
        contact.setName("Artur Correia");
        contact.setUrl("https://github.com/afarturc");

        Info info = new Info()
                .title("Altice Labs LabSeq Exercise 2023 API")
                .version("1.0")
                .contact(contact)
                .description("This API computes the LabSeq solution and cache information.");

        return new OpenAPI().info(info);
    }
}

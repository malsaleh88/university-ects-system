package be.ulb.ects.universityectssystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI universityEctsSystemApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("University ECTS System API")
                        .description("API for managing students, cours, and notes")
                        .version("1.0.0"));
    }
}

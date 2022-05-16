package dev.rmpedro.apirest.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiInfo {
    @Bean
    public OpenAPI springShopOpenAPI() {
       OpenAPI openAPI = new OpenAPI();

        openAPI.info(new Info().title("API Universidad Documentation").version("v.1.0.0").description("A traves de esta aplicacion podras gestionar la informacion personal de alumnos," +
                "profesores, maestros y empleados.\n" +
                "Tambien podras gestionar la informacion de las carreras asi como de la infraestructura" +
                "de la universidad: Pabellones y aulas").contact(new Contact().name("Pedro Reyes").email(
                        "rm.pedro@ibm.com"
        )));
        return openAPI;

    }



}

package com.digishopkart.customer_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAI(){
        return new OpenAPI()
                .info(new Info().title("DigiShopKart API")
                        .version("1.0")
                        .description("API documentation for DigiShopKart application"));
    }
}

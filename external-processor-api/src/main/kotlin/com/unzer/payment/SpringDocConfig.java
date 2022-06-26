package com.unzer.payment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfig implements WebMvcConfigurer {
	
	
	@Bean
	public OpenAPI metaData() {
		return new OpenAPI()
				.info(new Info().title("Demo Payment Process").description("Demo Payment Process")
						.version("1.0.0").license(new License().name("Apache 2.0").url("https://springdoc.org")))
				.externalDocs(new ExternalDocumentation());
	}


}

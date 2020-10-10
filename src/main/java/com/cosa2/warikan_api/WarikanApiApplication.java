package com.cosa2.warikan_api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class WarikanApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarikanApiApplication.class, args);
	}

	@Bean
	public OpenAPI warikanAPI(@Value("${springdoc.version}") String appVersion) {
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("Warikan API").version(appVersion)
						.license(new License().name("Apache 2.0").url("https://github.com/hudifu316/warikan_api")));
	}

}

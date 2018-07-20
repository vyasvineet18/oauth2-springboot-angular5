package com.server.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.models.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@Controller
public class RestAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestAuthServerApplication.class, args);
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		System.out.println(b.encode("password"));
	}
	
	@GetMapping("/protected")
	public void protectedPage() {}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	//localhost:8090/swagger-ui.html - for login
	private ApiInfo apiInfo() {
		ApiInfo apiInfo =new ApiInfo(
				"API Swagger",
				"API Auth Server Multi Login.",
				"1.0",
				"Vinit B. Vyas",
				"Vinit Vyas",
				"Apache License 2.0",
				"https://www.apache.org/licenses/LICENSE-2.0");
		return apiInfo;
	}
}
	
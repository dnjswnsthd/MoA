package com.moa.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// http://localhost:8000/moa/swagger-ui.html

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	private String version = "v0.1";
	private String title = "MoA API " + version;

	@Bean
	public Docket api() {
		List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
		responseMessages.add(new ResponseMessageBuilder().code(200).message("OK !!!").build());
		responseMessages.add(new ResponseMessageBuilder().code(500).message("서버 문제 발생 !!!")
				.responseModel(new ModelRef("Error")).build());
		responseMessages.add(new ResponseMessageBuilder().code(404).message("페이지를 찾을 수 없습니다 !!!").build());
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName(version).select()
				.apis(RequestHandlerSelectors.basePackage("com.moa.controller")).paths(postPaths()).build()
				.useDefaultResponseMessages(false).globalResponseMessage(RequestMethod.GET, responseMessages);
	}

	private Predicate<String> postPaths() {
		return PathSelectors.any();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(title)
				.description(
						"<h3>MoA API Reference for Developers</h3>Swagger를 이용한 VUEJS API<br><img src=\"logo.png\">")
				.version("0.1").build();
	}
}

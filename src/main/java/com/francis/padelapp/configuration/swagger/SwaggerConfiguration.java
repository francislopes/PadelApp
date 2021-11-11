package com.francis.padelapp.configuration.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;
import java.time.LocalTime;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket padelApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("API")
                .directModelSubstitute(LocalTime.class, String.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.francis.padelapp.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiEndPointsInfo()).directModelSubstitute(Timestamp.class, Long.class);
    }


    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder()
                .title("Padle API")
                .description("This API will provide the tools to setup an manage padel games. " +
                        "The users will be able to create a game, add participants, setup the score, etc.")
                .version("1.0.0")
                .build();
    }
}

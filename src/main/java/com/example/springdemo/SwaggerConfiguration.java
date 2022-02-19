package com.example.springdemo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    private static final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

    @Value("${swagger.api.title}")
    private String swaggerApiTitle;

    @Bean
    public Docket springDemoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.springdemo"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(swaggerApiTitle)
                .description("Spring Demo API reference for developers")
                .termsOfServiceUrl("http://localhost:8080")
                .license("SpringDemo License")
                .licenseUrl("http://localhost:8080")
                .version("1.0")
                .build();
    }

}

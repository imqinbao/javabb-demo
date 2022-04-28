package cn.javabb.admin.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

/**
 * Swagger生成api文档
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {
    @Value("${swagger.host:}")
    private String host;

    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        if ((host != null && !host.trim().isEmpty())) docket.host(host);
        return docket
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.javabb"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("javabb-demo-admin接口文档")
                .description("javabb-demo-admin接口文档")
                .version("1.0")
                .termsOfServiceUrl("")
                .build();
    }

    private List<SecurityScheme> securitySchemes() {
        return Collections.singletonList(new ApiKey("Authorization", "Authorization", "header"));
    }

    private List<SecurityContext> securityContexts() {
        AuthorizationScope[] scopes = {new AuthorizationScope("global", "accessEverything")};
        List<SecurityReference> references = Collections.singletonList(new SecurityReference("Authorization", scopes));
        return Collections.singletonList(SecurityContext.builder()
                .securityReferences(references)
                .forPaths(PathSelectors.any())
                .build());
    }

}

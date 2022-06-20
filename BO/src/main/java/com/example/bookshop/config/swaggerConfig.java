package com.example.bookshop.config;/**
 * @Auther: shu'ai
 * @Date: 2021/10/15 15:53
 * @Description:
 */

/**
 * @ClassName:swaggerConfig
 * @Auther: shu'ai
 * @Description:swagger
 * @Date: 2021/10/15 15:53
 * @Version: v1.0
 */




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@Configuration
@EnableSwagger2
public class swaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(Collections.singleton("https"))
                .host("127.0.0.1")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.bookshop.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("博客api文档")
                .description("springboot+vue")
                .termsOfServiceUrl("127.0.0.1")
                .version("1.0")
                .build();
    }
}

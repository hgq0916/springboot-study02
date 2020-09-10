package com.ruigu.springboot.study;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author hugangquan
 * @date 2020/09/10 14:32
 */
@Configuration
public class SpringConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ruigu.springboot.study"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试swagger-knife4j")
                .description("测试swagger-knife4j")
                //.termsOfServiceUrl("http://192.168.1.198:10070/platformgroup/ms-admin")
                //.contact("程序猿")
                .version("1.0")
                .build();
    }

}

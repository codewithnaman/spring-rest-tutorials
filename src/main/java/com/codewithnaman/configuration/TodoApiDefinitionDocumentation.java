package com.codewithnaman.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class TodoApiDefinitionDocumentation {

    public static final Contact CONTACT
            = new Contact(
            "Naman Gupta",
            "http://www.codewithnaman.com",
            "tech.naman.gupta@gmail.com");
    public static final ApiInfo API_INFO
            = new ApiInfo(
            "ToDo Application Service Documentation",
            "This Contains the ToDo Application endpoints in the OpenAPI Specification format 3.0",
            "1.0",
            "urn:tos",
            CONTACT,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList<>());

    public static final HashSet<String> DEFAULT_PRODUCE_CONSUME = new HashSet<>(Arrays.asList("application/json","application/xml"));

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(API_INFO)
                .produces(DEFAULT_PRODUCE_CONSUME)
                .consumes(DEFAULT_PRODUCE_CONSUME)
                .select().apis(RequestHandlerSelectors.basePackage("com.codewithnaman"))
                .build();
    }
}

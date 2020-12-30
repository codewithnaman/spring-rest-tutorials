package com.codewithnaman;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.codewithnaman.respository"})
@EntityScan(basePackages = {"com.codewithnaman.entity"})
public class RESTApplication {

    public static void main(String[] args) {
        SpringApplication.run(RESTApplication.class, args);
    }
}

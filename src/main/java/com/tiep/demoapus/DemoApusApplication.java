package com.tiep.demoapus;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(title = "API Documentation", version = "1.0", description = "API for My Application")
)
@SpringBootApplication
public class DemoApusApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApusApplication.class, args);
    }

}

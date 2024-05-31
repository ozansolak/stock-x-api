package com.ozan.hub.stockxapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(servers = {@Server(url = "${com.ozan.hub.url}")},
        info = @Info(title = "Stock Exchange Service", version = "0.0.1", description = "Stock Exchange Api Developer Guide"))
@SpringBootApplication
public class StockXApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockXApiApplication.class, args);
    }

}

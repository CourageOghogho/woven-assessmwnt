package com.woven.wovenfinance;

import com.woven.wovenfinance.config.jwt.RSAKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RSAKeyProperties.class)
@SpringBootApplication
public class WovenFinanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WovenFinanceApplication.class, args);
    }

}

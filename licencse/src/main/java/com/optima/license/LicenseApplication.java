package com.optima.license;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
@Slf4j
public class LicenseApplication {

    public static void main(String[] args) {
        val context = SpringApplication.run(LicenseApplication.class, args);
        String dfInfo = context.getEnvironment().getProperty("spring.datasource.username")
                + "@" + context.getEnvironment().getProperty("spring.datasource.url");
        log.info("DB connection : {}", dfInfo);
    }

}

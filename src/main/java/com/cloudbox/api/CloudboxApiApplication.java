package com.cloudbox.api;

import com.cloudbox.api.config.amazon.S3Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(S3Config.class)
public class CloudboxApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudboxApiApplication.class, args);
    }
}

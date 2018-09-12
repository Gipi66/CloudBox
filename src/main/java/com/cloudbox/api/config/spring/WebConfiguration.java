package com.cloudbox.api.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.constraints.NotNull;

@Configuration
@ComponentScan(basePackages = "com.cloudbox.api.controller")
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(@NotNull final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/docs/index.html")
                .addResourceLocations("classpath:/static/docs");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

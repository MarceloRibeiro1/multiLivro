package com.fcamara.multilivro.rent.configuration;

import com.fcamara.multilivro.rent.handler.ValidationInterceptor;
import com.fcamara.multilivro.rent.repository.RentRepository;
import com.fcamara.multilivro.rent.validation.RentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class RentWebConfiguration implements WebMvcConfigurer {

    private final RentValidator rentValidator;
    private final RentRepository rentRepository;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ValidationInterceptor(rentValidator, rentRepository));
    }
}

package com.fcamara.multilivro.rent.configuration;

import com.fcamara.multilivro.rent.repository.RentRepository;
import com.fcamara.multilivro.rent.validation.RentValidator;
import com.fcamara.multilivro.rent.validation.validators.UniqueRentPerActiveBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentConfiguration {
    private final RentRepository repository;

    @Autowired
    public RentConfiguration(RentRepository repository) {
        this.repository = repository;
    }

    @Bean
    public RentValidator rentValidator() {
        RentValidator rentValidator = new UniqueRentPerActiveBook(repository);
        return rentValidator;
    }
}

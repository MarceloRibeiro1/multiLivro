package com.fcamara.multilivro.rent.configuration;

import com.fcamara.multilivro.rent.repository.RentRepository;
import com.fcamara.multilivro.rent.validation.RentValidator;
import com.fcamara.multilivro.rent.validation.validators.UniqueRentPerActiveBook;
import com.fcamara.multilivro.rent.validation.validators.ValidateCurrentUserRent;
import com.fcamara.multilivro.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentConfiguration {
    private final RentRepository rentRepository;
    private final AuthService authService;

    @Autowired
    public RentConfiguration(AuthService authService, RentRepository rentRepository) {
        this.authService = authService;
        this.rentRepository = rentRepository;
    }

    @Bean
    public RentValidator rentValidator() {
        RentValidator rentValidator = new ValidateCurrentUserRent(authService);

        rentValidator.linkWith(new UniqueRentPerActiveBook(rentRepository));

        return rentValidator;
    }


}

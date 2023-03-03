package com.fcamara.multilivro.rent.validation.validators;

import com.fcamara.multilivro.exception.LogLevel;
import com.fcamara.multilivro.rent.exception.DuplicateRentEntryException;
import com.fcamara.multilivro.rent.exception.UserDoesNotMatchException;
import com.fcamara.multilivro.rent.model.Rent;
import com.fcamara.multilivro.rent.validation.RentValidator;
import com.fcamara.multilivro.user.model.AppUser;
import com.fcamara.multilivro.user.service.AuthService;
import org.springframework.http.HttpStatus;

public class ValidateCurrentUserRent extends RentValidator {

    private final AuthService authService;

    public ValidateCurrentUserRent(AuthService authService) {
        this.authService = authService;
    }


    @Override
    public void validate(Rent rent) throws DuplicateRentEntryException {
        AppUser currentUser = authService.getCurrentUser();

        if (!rent.getUser().getId().equals(currentUser.getId()))
            throw new UserDoesNotMatchException("User not allowed", HttpStatus.UNAUTHORIZED, LogLevel.INFO);

        checkNext(rent);
    }
}

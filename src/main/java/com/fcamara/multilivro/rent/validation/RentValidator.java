package com.fcamara.multilivro.rent.validation;

import com.fcamara.multilivro.rent.exception.RentValidationException;
import com.fcamara.multilivro.rent.model.Rent;

public abstract class RentValidator {
    private RentValidator nextValidator;

    public RentValidator linkWith(RentValidator nextValidator) {
        this.nextValidator = nextValidator;
        return nextValidator;
    }

    public abstract void validate(Rent rent) throws RentValidationException;

    protected void checkNext(Rent rent) throws RentValidationException {
        if (nextValidator != null) {
            nextValidator.validate(rent);
        }
    }
}

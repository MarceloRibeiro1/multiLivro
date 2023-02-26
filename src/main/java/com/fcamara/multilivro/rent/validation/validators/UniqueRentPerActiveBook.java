package com.fcamara.multilivro.rent.validation.validators;

import com.fcamara.multilivro.exception.LogLevel;
import com.fcamara.multilivro.rent.exception.DuplicateRentEntryException;
import com.fcamara.multilivro.rent.model.Rent;
import com.fcamara.multilivro.rent.model.RentState;
import com.fcamara.multilivro.rent.repository.RentRepository;
import com.fcamara.multilivro.rent.validation.RentValidator;
import org.springframework.http.HttpStatus;

public class UniqueRentPerActiveBook extends RentValidator {

    private final RentRepository rentRepository;

    public UniqueRentPerActiveBook(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    @Override
    public void validate(Rent rent) throws DuplicateRentEntryException {
        Iterable<Rent> allUserRents = rentRepository.findAllByUserAndBookAndDeletedFalse(rent.getUser(), rent.getBook());

        allUserRents.forEach(r -> {
            if(r.getId().equals(rent.getId())) return;
            if (
                    r.getState() == RentState.PARALIZED ||
                    r.getState() == RentState.FINALIZED ||
                    r.getState() == RentState.ARCHIVED
            ) throw new DuplicateRentEntryException("Duplicate rent entry, reactivate previous rent", HttpStatus.CONFLICT, LogLevel.WARN);
            throw new DuplicateRentEntryException("Duplicate rent entry, finish first rent", HttpStatus.CONFLICT, LogLevel.WARN);
        });

        checkNext(rent);
    }
}

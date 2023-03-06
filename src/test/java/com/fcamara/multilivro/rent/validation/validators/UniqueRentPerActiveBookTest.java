package com.fcamara.multilivro.rent.validation.validators;

import com.fcamara.multilivro.rent.exception.DuplicateRentEntryException;
import com.fcamara.multilivro.rent.model.Rent;
import com.fcamara.multilivro.rent.model.RentState;
import com.fcamara.multilivro.rent.repository.RentRepository;
import com.fcamara.multilivro.rent.validation.RentValidator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class UniqueRentPerActiveBookTest {

    @Mock
    RentRepository rentRepository;
    @Mock
    RentValidator nextValidator;

    @InjectMocks
    UniqueRentPerActiveBook validator;

    @Test
    void ShowldThrowWhenRentIsAlreadyInCourse() {
        Mockito.when(rentRepository.findAllByUserAndBookAndDeletedFalse(Mockito.any(), Mockito.any()))
                .thenReturn(
                Arrays.asList(
                        new Rent(UUID.randomUUID())
                )
        );

        Rent rent = new Rent(UUID.randomUUID());

        DuplicateRentEntryException ex = assertThrows(DuplicateRentEntryException.class, () -> validator.validate(rent));

        assertEquals("Duplicate rent entry: rent already in course",ex.getMessage());
    }

    @Test
    void ShowldThrowWhenRentIsDeactivated() {
        Rent rent1 = new Rent(UUID.randomUUID());
        rent1.setState(RentState.FINALIZED);
        Mockito.when(rentRepository.findAllByUserAndBookAndDeletedFalse(Mockito.any(), Mockito.any()))
                .thenReturn(
                Arrays.asList(
                        rent1
                )
        );

        Rent rent = new Rent(UUID.randomUUID());

        DuplicateRentEntryException ex = assertThrows(DuplicateRentEntryException.class, () -> validator.validate(rent));

        assertEquals("Duplicate rent entry: reactivate previous rent",ex.getMessage());
    }

    @Test
    void ShowldCallNextRentWhenValidateIsSuccess() {
        UUID id = UUID.randomUUID();

        Mockito.when(rentRepository
                        .findAllByUserAndBookAndDeletedFalse(Mockito.any(), Mockito.any()))
                .thenReturn(
                        Arrays.asList(
                                new Rent(id)
                        )
                );

        Rent rent = new Rent(id);

        validator.linkWith(nextValidator);

        validator.validate(rent);

        Mockito.verify(nextValidator,Mockito.times(1)).validate(rent);
    }
    @Test
    void ShowldCallNextRentWhenThersNoOtherRent() {
        UUID id = UUID.randomUUID();

        Mockito.when(rentRepository
                        .findAllByUserAndBookAndDeletedFalse(Mockito.any(), Mockito.any()))
                .thenReturn(
                        new ArrayList<>()
                );

        Rent rent = new Rent(id);

        validator.linkWith(nextValidator);

        validator.validate(rent);

        Mockito.verify(nextValidator,Mockito.times(1)).validate(rent);
    }

}
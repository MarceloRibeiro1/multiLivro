package com.fcamara.multilivro.rent.validation.validators;

import com.fcamara.multilivro.book.model.Book;
import com.fcamara.multilivro.rent.exception.UserDoesNotMatchException;
import com.fcamara.multilivro.rent.model.Rent;
import com.fcamara.multilivro.rent.validation.RentValidator;
import com.fcamara.multilivro.user.model.AppUser;
import com.fcamara.multilivro.user.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ValidateCurrentUserRentTest {

    @Mock
    AuthService authService;
    @Mock
    RentValidator nextValidator;

    @InjectMocks
    ValidateCurrentUserRent validator;

    @Test
    void ShowldThrowWhenRentDoesNotBelongToCurrentUser() {
        AppUser user1 = new AppUser();
        user1.setId(UUID.randomUUID());
        Mockito.when(authService.getCurrentUser()).thenReturn(user1);

        AppUser user2 = new AppUser();
        user2.setId(UUID.randomUUID());

        Rent rent = new Rent(user2,new Book());

        assertThrows(UserDoesNotMatchException.class,() -> validator.validate(rent));
    }

    @Test
    void ShowldCallNextRentWhenValidateIsSuccess() {
        AppUser user = new AppUser();
        user.setId(UUID.randomUUID());
        Mockito.when(authService.getCurrentUser()).thenReturn(user);

        Rent rent = new Rent(user,new Book());

        validator.linkWith(nextValidator);

        validator.validate(rent);

        Mockito.verify(nextValidator,Mockito.times(1)).validate(rent);
    }
}
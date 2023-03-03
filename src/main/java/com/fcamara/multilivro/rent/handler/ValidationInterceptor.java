package com.fcamara.multilivro.rent.handler;

import com.fcamara.multilivro.rent.controller.RentController;
import com.fcamara.multilivro.rent.model.Rent;
import com.fcamara.multilivro.rent.repository.RentRepository;
import com.fcamara.multilivro.rent.validation.RentValidator;
import com.fcamara.multilivro.user.model.AppUser;
import com.fcamara.multilivro.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class ValidationInterceptor implements HandlerInterceptor {
    private final RentValidator validator;
    private final RentRepository repository;
    private final AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (handlerMethod.getBeanType().equals(RentController.class)) {
                getRentFromRequest(request).ifPresent(validator::validate);
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private Optional<Rent> getRentFromRequest(HttpServletRequest request) {
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String uuid = pathVariables.get("rentId");
        if (isNull(uuid)) {
            String bookId = pathVariables.get("bookId");
            if (nonNull(bookId)) {
                AppUser currentUser = authService.getCurrentUser();
                return Optional.of(new Rent(currentUser, bookId));
            }
            return Optional.empty();
        };
        return repository.findById(UUID.fromString(uuid));
    }

}

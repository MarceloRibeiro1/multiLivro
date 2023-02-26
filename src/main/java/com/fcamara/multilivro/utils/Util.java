package com.fcamara.multilivro.utils;

import com.fcamara.multilivro.exception.BasicException;
import com.fcamara.multilivro.exception.DefaultAbstractException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
@Slf4j
public class Util {
    public static void logger(
            String method,
            Class className,
            String domain,
            DefaultAbstractException exception,
            Optional<Object> value){
        String message = MessageFormat.format(
                "{0} from class: [{1}] in domain {2} throwed: {3}\nPayload: {4}",
                method,
                className.getName(),
                domain.toUpperCase(),
                exception.getMessage(),
                value.orElse("null")
        );

        switch (exception.getLogLevel()) {
            case INFO:
                log.info(message);
                break;
            case WARN:
                log.warn(message);
                break;
            case ERROR:
                log.error(message);
                break;
            case DEBUG:
                log.debug(message);
        }
    }

    public static void logger(
            String method,
            Class className,
            String domain,
            DefaultAbstractException exception){
        logger(method, className, domain, exception, Optional.empty());
    }

    public static void logger(
            String method,
            Class className,
            String domain,
            Exception exception){
        logger(method, className, domain, new BasicException(exception.getMessage()), Optional.empty());
    }

    public static void logger(
            String method,
            Class className,
            String domain,
            Exception exception,
            Optional<Object> object){
        logger(method, className, domain, new BasicException(exception.getMessage()), object);
    }

    public static Pageable getPageable(
            int page,
            int size,
            Sort.Direction direction) {
        return PageRequest.of(page, size, Sort.by(direction));
    }
    public static Pageable getPageable(
            int page,
            int size,
            Sort.Direction direction,
            String[] sort) {

        if (isNull(sort)) return getPageable(page, size, direction);

        return PageRequest.of(page, size, Sort.by(direction,sort));
    }
}

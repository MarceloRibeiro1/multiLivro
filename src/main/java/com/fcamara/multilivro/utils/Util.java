package com.fcamara.multilivro.utils;

import com.fcamara.multilivro.exception.DefaultAbstractException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Optional;

@Service
@Slf4j
public class Util {
    public static void logger(
            Method method,
            String domain,
            DefaultAbstractException exception,
            Optional<Object> value){
        String message = MessageFormat.format(
                "{0} from class: [{1}] in domain {2} throwed: {3}\nPayload: {4}",
                method.getName(),
                method.getDeclaringClass().getName(),
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
            Method method,
            String domain,
            DefaultAbstractException exception){
        logger(method, domain, exception, Optional.empty());
    }
}

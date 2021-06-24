package com.customer.api.exception.bundle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.Objects.nonNull;

@Component
public class ExceptionMessageResource {

    private static final String BASE_BUNDLE = "error/messages";

    @Value("${message-error.language}")
    private String language;

    @Value("${message-error.country}")
    private String country;

    public String findMessage(final String key) {
        if (nonNull(key)) {
            Locale locale = new Locale(language, country);
            ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_BUNDLE, locale);
            try {
                return resourceBundle.getString(key);
            } catch (Exception ex) {
                return key;
            }
        }
        return null;
    }
}
package com.coveo.challenge.converter;

import com.coveo.challenge.model.AdminCode;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.equalsAny;

@Component
public class AdminCodeConverter implements Converter<String, AdminCode> {
    @Override
    public String convertToX(AdminCode value) {
        return null;
    }

    @Override
    public AdminCode convertToY(String value) {
        Optional<AdminCode> adminCodeFound = Arrays.stream(AdminCode.values())
                .filter(v -> equalsAny(v.getCode(), v.getFullName()))
                .findFirst();
        return adminCodeFound.isPresent() ? adminCodeFound.get() : null;
    }
}
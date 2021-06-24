package com.customer.api.service.impl;

import com.customer.api.service.BirthDateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BirthDateServiceImpl implements BirthDateService {

    public int getAge(final LocalDate birthDate) {

        var date = LocalDate.now();
        int age = date.getYear() - birthDate.getYear();

        return verifyAge(date, birthDate) ? age : age - 1;
    }

    private boolean verifyAge(final LocalDate date, final LocalDate birthDate) {
        if (date.getMonth().getValue() > birthDate.getMonth().getValue()) {
            return Boolean.TRUE;
        } else if (date.getMonth().getValue() == birthDate.getMonth().getValue()
            && birthDate.getDayOfMonth() <= date.getDayOfMonth()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
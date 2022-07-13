package com.dentist.util;

import org.springframework.stereotype.Component;

@Component
public interface IValidation<T> {
    public boolean validate(T t);
}

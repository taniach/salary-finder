package com.example.salaryfinder.helper;

import com.example.salaryfinder.domain.CustomSortOrder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToEnumConverter implements Converter<String, CustomSortOrder> {
    @Override
    public CustomSortOrder convert(String source) {
        try {
            return CustomSortOrder.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Sort order value is not valid");
        }
    }
}

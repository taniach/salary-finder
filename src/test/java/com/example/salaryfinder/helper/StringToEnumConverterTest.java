package com.example.salaryfinder.helper;

import com.example.salaryfinder.domain.CustomSortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StringToEnumConverterTest {
    @Autowired
    private StringToEnumConverter stringToEnumConverter;

    @Test
    public void testStringToEnum_success() {
        assertEquals(CustomSortOrder.NAME, stringToEnumConverter.convert("name"));
        assertEquals(CustomSortOrder.SALARY, stringToEnumConverter.convert("Salary"));
        assertEquals(CustomSortOrder.SALARY, stringToEnumConverter.convert("SALARY"));
    }

    @Test
    public void testStringToEnum_invalidInput() {
        assertThrows(IllegalArgumentException.class, () -> stringToEnumConverter.convert("name2"));
    }
}

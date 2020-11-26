package com.silasonyango.transactionservice.utils;

import com.silasonyango.transactionservice.utility_classes.UtilityClass;
import com.silasonyango.transactionservice.utility_classes.Utils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilsTest {
    @Test
    public void toCamelCase_works_properly() {
        assertEquals("Sila Onyango", Utils.toCamelCase("SILAS ONYANGO"));
    }

}

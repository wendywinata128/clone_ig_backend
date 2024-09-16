package com.commsult_test.clone_ig.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FunctionUtil {
    public static String getCurrentDateInStringFormatted(){
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

        return now.format(dateTimeFormatter);
    }
}

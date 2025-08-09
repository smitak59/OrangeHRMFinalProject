package com.hrm.util;

import org.apache.commons.lang3.RandomStringUtils;

public class TestUtil {
	
	public static String generateRandomFirstName() {
        return "AutoFN_" + RandomStringUtils.randomAlphabetic(5);
    }

    public static String generateRandomLastName() {
        return "AutoLN_" + RandomStringUtils.randomAlphabetic(5);
    }

}

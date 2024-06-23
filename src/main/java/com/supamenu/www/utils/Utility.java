package com.supamenu.www.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Utility {
    public static int generateOTP() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

    public static final Logger logger = LoggerFactory.getLogger(Utility.class);
}

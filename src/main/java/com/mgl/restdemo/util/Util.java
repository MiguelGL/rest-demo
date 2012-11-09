package com.mgl.restdemo.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;

public class Util {

    private Util() {}

    public static String md5Sum(String text) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
        byte[] md5 = digest.digest(text.getBytes());
        BigInteger bigInt = new BigInteger(1, md5);
        return StringUtils.leftPad(bigInt.toString(16), 32, '0');
    }
    
}

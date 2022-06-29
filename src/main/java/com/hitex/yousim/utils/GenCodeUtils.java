package com.hitex.yousim.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Random;

@Log4j2
public class GenCodeUtils {
    private static final String ALGO = "AES";
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    public static final String ORDER_PREFIX = "OD";
    public static final String VOUCHER_PREFIX = "VC";
    private static final String DATA_FOR_RANDOM_STRING = CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();

    public static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            // 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            sb.append(rndChar);
        }
        return sb.toString();
    }
    public static String genOrderCode() {
        return ORDER_PREFIX + generateRandomString(7);
    }
    public static String genVoucherCodeV2() {
        return VOUCHER_PREFIX + generateRandomString(7);
    }

    public static String encrypt(String Data, String textKey, String salt) {
        try {
            Key key = generateKey(textKey, salt);
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(Data.getBytes());
            System.out.println(encVal);
            String encryptedValue = Base64.encodeBase64String(encVal);
            return encryptedValue;
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    public static String decrypt(String encryptedData, String textKey, String salt) {
        try {
            Security.setProperty("crypto.policy", "unlimited");
            Key key = generateKey(textKey, salt);
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue =Base64.decodeBase64(encryptedData);
            byte[] decValue = c.doFinal(decordedValue);
            String decryptedValue = new String(decValue);
            return decryptedValue;
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }


    private static Key generateKey(String keyText, String salt) {
        Key key = new SecretKeySpec((keyText + salt).getBytes(), ALGO);
        return key;
    }

    public static Long genVoucherCode() {
        Long currentTime = System.currentTimeMillis();
        Long firstCode = currentTime % 100000000000L;
        Random random = new Random(System.nanoTime());
        Long x1 = random.nextInt(9) + 1L;
//        System.out.println("x1" + x1);

        Long x2 = Long.valueOf(random.nextInt(10));
//        System.out.println("x2 " + x2);

        Long a = Long.valueOf(random.nextInt(10));
//        System.out.println("a " + a);


        Long b = Long.valueOf(random.nextInt(10));
//        System.out.println("b " + b);

        Long c = Long.valueOf(random.nextInt(10));
//        System.out.println("c " + c);

        Long d = Long.valueOf(random.nextInt(10));
//        System.out.println("d " + d);

        Long e = Long.valueOf(random.nextInt(10));
//        System.out.println("e " + e);

        Long f = Long.valueOf(random.nextInt(10));
//        System.out.println("f " + f);

        Long g = Long.valueOf(random.nextInt(10));
//        System.out.println("g " + g);

        Long h = Long.valueOf(random.nextInt(10));
//        System.out.println("h " + h);

        Long i = Long.valueOf(random.nextInt(10));
//        System.out.println("i " + i);

        Long j = Long.valueOf(random.nextInt(10));
//        System.out.println("j " + j);

        Long k = Long.valueOf(random.nextInt(10));
//        System.out.println("k " + k);

        Long x3 = (x1 + a + b + c + d + e + f + g + h + i + j + k) % 10;
//        System.out.println("x3 " + x3);

        Long voucher = x1 * 10000000000000L + d * 1000000000000L + e * 100000000000L + f * 10000000000L
                + a * 1000000000L + g * 100000000L + h * 10000000L + i * 1000000L + b * 100000L + j * 10000L
                + k * 1000L + x2 * 100L + c * 10L + x3;

        return voucher;
    }
}

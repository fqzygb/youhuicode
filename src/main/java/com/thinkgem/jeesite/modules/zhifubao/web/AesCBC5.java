package com.thinkgem.jeesite.modules.zhifubao.web;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesCBC5 {

    //加密

    private static final String key = "@jmltzfbxmfqzyxy";
    private static final String initVector = "encryptionIntVec";

    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //解密
    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }


    public static void main(String[] args) {
//        String originalString = "password1234567";
//        System.out.println("Original String to encrypt - " + originalString);
//        String encryptedString = encrypt(originalString);
//        System.out.println("Encrypted String - " + encryptedString);
//        String decryptedString = decrypt(encryptedString);
        String decryptedString = decrypt("DSBqjEL9QTroTKx3IeESS3tlF/iyIsYdI3xew4cqfWh7ytJ8zwoBy6CzJfSSS3A6CR1qRWg4p1lE4x5am3+i3XpUY+ApXUVAllwz2qvW63nSUGySvTZXXyA7jPpYiycFMav6AfVRMdbaNTRH6ESPDPrXTD9UAkLsvF2GxYeOVBdpm091a1WSCtUu8VfAKJWF1S7FMNzjQNhz3Bqh5VLSYs61SSwZCsG+/DW91GzVaIC1TllYH45j40DGZHaM1FTX5Jn/4sZUpeQBT0mjAfYKxR5tKkZVhstUw7BVcj9LLZbPw/26SNjTlgi/e3gxpmFoCCO1vVT8UC183ior6MMKyZ5mjRQuIBpUN4VzZSVvycCiPVKKwDxMfcI1pXbsIaCEbOSAODhdbe0GtDC6Fql5YNgKj3SSIJ1hb1HuEAb1pVjiGWP4ZFLxrH4iMDFZFcUXm909BWqMgU7M2ongTgBMCsmlrhquYsmQV1vDZBCbbwouxQmTDfb55o2FxH4QBKCZtmyegjp7XKJwYhwaHgr2OqRHocuC4QcXbcXbWdUMoW4WcsDtZqaqdgRnLkKnh5E9HwYheC3Pu/aYlcI4Xo5L/A==");
        System.out.println("After decryption - " + decryptedString);
    }

}

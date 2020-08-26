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
        String decryptedString = decrypt("ZNK31rzqTWlm3RN6xJMEqsPnwMRgytLTB2+GXjRoGfOUKSntX5//1qiSlL5nRpcHQ4BZdf/2da29u59wSaXUKw8Zw2tJcvtwzDRaQuhS5IQ=");
        System.out.println("After decryption - " + decryptedString);
    }

}

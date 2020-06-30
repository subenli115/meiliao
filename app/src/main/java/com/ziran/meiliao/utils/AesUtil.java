package com.ziran.meiliao.utils;
import android.util.Log;

import com.umeng.socialize.net.utils.Base64;
import com.ziran.meiliao.common.security.AES;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密，CBC模式，zeropadding填充，偏移量为key
 */
public class AesUtil {
    private static AesUtil INSTANCE;

    public static AesUtil get() {
        if (INSTANCE == null) {
            synchronized (AesUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AesUtil();
                }
            }
        }
        return INSTANCE;
    }

    //加密
    public static String encrypt(String data) {

        String ivString = "pigxpigxpigxpigx";
        //偏移量
        byte[] iv = ivString.getBytes();
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes();
            int length = dataBytes.length;
            //计算需填充长度
            if (length % blockSize != 0) {
                length = length + (blockSize - (length % blockSize));
            }
            byte[] plaintext = new byte[length];
            //填充
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keySpec = new SecretKeySpec(ivString.getBytes(), "AES");
            //设置偏移量参数
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] encryped = cipher.doFinal(plaintext);

            return Base64.encodeBase64String(encryped);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //解密
    public static String desEncrypt(String data) {

        String ivString = "pigxpigxpigxpigx";
        byte[] iv = ivString.getBytes();

        try {
            byte[] encryp = Base64.decodeBase64(data);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(ivString.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] original = cipher.doFinal(encryp);
            return new String(original);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
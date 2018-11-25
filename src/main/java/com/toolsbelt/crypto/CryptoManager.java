package com.toolsbelt.crypto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@Component("cryptoManager")
@EnableConfigurationProperties
public class CryptoManager {

    private static SecretKeySpec secretKeySpec;

    private static byte[] key;

    @Value("${security-crypto.secret-key}")
    private String secretKey;

    @Value("${security-crypto.charset}")
    private String charsetName;

    @Value("${security-crypto.digest}")
    private String messageDigest;

    @Value("${security-crypto.basic-algorithm}")
    private String basicAlgorithm;

    @Value("${security-crypto.complex-algorithm}")
    private String complexAlgorithm;

    private void setKey() {
        MessageDigest sha = null;
        try {
            key = this.secretKey.getBytes(this.charsetName);
            sha = MessageDigest.getInstance(this.messageDigest);
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKeySpec = new SecretKeySpec(key, this.basicAlgorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String decrypt(String strToDecrypt) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance(this.complexAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public String encrypt(String strToEncrypt) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance(this.complexAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
}

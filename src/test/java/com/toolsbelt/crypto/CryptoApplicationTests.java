package com.toolsbelt.crypto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CryptoApplicationTests {

    @Autowired
    private CryptoManager cryptoManager;

    final String BASIC_TEXT = "blabla";

    @Test
    public void encryptValue() {

        String encrypt = this.cryptoManager.encrypt(BASIC_TEXT);
        Assert.assertNotEquals(BASIC_TEXT, encrypt);
    }

    @Test
    public void decryptValue() {

        String encrypt = this.cryptoManager.encrypt(BASIC_TEXT);
        String decrypt = this.cryptoManager.decrypt(encrypt);

        Assert.assertEquals(decrypt, BASIC_TEXT);
    }

    @SpringBootApplication
    @PropertySource("classpath:application.yml")
    static class TestConfiguration {
    }

}

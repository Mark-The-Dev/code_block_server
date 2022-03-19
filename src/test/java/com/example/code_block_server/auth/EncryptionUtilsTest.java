package com.example.code_block_server.auth;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplates;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

public class EncryptionUtilsTest {

    @Test
    public void encryptAndDecrypt() throws GeneralSecurityException {

        AeadConfig.register(); // Registering only Aead Primitive

        Aead aead = KeysetHandle.generateNew(KeyTemplates.get("AES256_GCM"))
                .getPrimitive(Aead.class);

        String secretKey = "TinkIsNotCrazy;-)";

        byte[] cipherText = aead.encrypt("Hello, World!".getBytes(StandardCharsets.UTF_8), secretKey.getBytes());
        String encryptedText  = Base64.getEncoder().encodeToString(cipherText);

        // Decrypting cipherText Works Fine
        byte[] decrypted = aead.decrypt(cipherText, secretKey.getBytes());
        System.out.println("decrypted from bytes: " + new String(decrypted));

        // Decrypting encryptedText doesn't Work
        decrypted = aead.decrypt(Base64.getDecoder().decode(encryptedText), secretKey.getBytes());
        System.out.println("decrypted from converted string: " + new String(decrypted));

        //assertEquals(decrypted, secretKey);
    }
}

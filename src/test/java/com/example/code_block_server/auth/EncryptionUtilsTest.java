package com.example.code_block_server.auth;

import com.google.crypto.tink.*;
import com.google.crypto.tink.hybrid.HybridConfig;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

public class EncryptionUtilsTest {

    @Test
    public void encryptAndDecrypt() throws GeneralSecurityException {

        HybridConfig.register();

        KeysetHandle privateKeySetHandle = KeysetHandle.generateNew(
                KeyTemplates.get("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM"));
        KeysetHandle publicKeySetHandle = privateKeySetHandle.getPublicKeysetHandle();
        HybridEncrypt encrypt = publicKeySetHandle.getPrimitive(HybridEncrypt.class);
        HybridDecrypt decrypt = privateKeySetHandle.getPrimitive(HybridDecrypt.class);

        String inputString = "Hello, world!";
        byte[] inputBytes = inputString.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedBytes1 = encrypt.encrypt(inputBytes, null);
        String encryptedString  = Base64.getEncoder().encodeToString(encryptedBytes1);
        byte[] encryptedBytes2 = Base64.getDecoder().decode(encryptedString);
        byte[] decryptedBytes = decrypt.decrypt(encryptedBytes2, null);
        String decryptedString = new String(decryptedBytes);
        System.out.println("decrypted from converted string: " + decryptedString);
    }
}

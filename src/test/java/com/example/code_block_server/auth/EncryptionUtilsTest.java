package com.example.code_block_server.auth;

import com.example.code_block_server.dto.PublicKeyDTO;
import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.hybrid.HybridConfig;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncryptionUtilsTest {

    @Test
    public void encryptAndDecrypt() throws GeneralSecurityException, IOException {

        HybridConfig.register();
        PublicKeyDTO publicKeyDTO = EncryptionUtils.getPublicKey();
        KeysetHandle publicKeySetHandle = publicKeyDTO.getPublicKeySetHandle();
        HybridEncrypt hybridEncrypt = publicKeySetHandle.getPrimitive(HybridEncrypt.class);

        String inputString = "Hello, world!";
        byte[] encryptedBytes = hybridEncrypt.encrypt(inputString.getBytes(StandardCharsets.UTF_8), null);

        String decryptedString = EncryptionUtils.decrypt(encryptedBytes);
        assertEquals(inputString, decryptedString);
    }
}

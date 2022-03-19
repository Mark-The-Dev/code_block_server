package com.example.code_block_server.auth;

import com.example.code_block_server.dto.PublicKeyDTO;
import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.JsonKeysetReader;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.hybrid.HybridConfig;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncryptionUtilsTest {

    @Test
    public void encryptAndDecrypt() throws GeneralSecurityException, IOException {

        HybridConfig.register();
        PublicKeyDTO publicKeyDTO = EncryptionUtils.getPublicKey();
        ByteArrayInputStream is = new ByteArrayInputStream(publicKeyDTO.getEncodedPublicKeySetHandle());
        KeysetHandle publicKeySetHandle = CleartextKeysetHandle.read(JsonKeysetReader.withInputStream(is));
        HybridEncrypt hybridEncrypt = publicKeySetHandle.getPrimitive(HybridEncrypt.class);

        String inputString = "Hello, world!";
        byte[] encryptedBytes = hybridEncrypt.encrypt(inputString.getBytes(StandardCharsets.UTF_8), null);
        String encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);

        String decryptedString = EncryptionUtils.decrypt(encryptedString);
        assertEquals(inputString, decryptedString);
    }
}

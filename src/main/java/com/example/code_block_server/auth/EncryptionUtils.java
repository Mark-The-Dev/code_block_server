package com.example.code_block_server.auth;

import com.google.crypto.tink.*;
import com.google.crypto.tink.hybrid.HybridConfig;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/*
 * Documentation:
 * https://github.com/google/tink/blob/master/docs/JAVA-HOWTO.md
 * https://developers.google.com/tink/hybrid
 */

public class EncryptionUtils {

    private static final String filePath = "./src/main/resources/key-set.json";

    // TODO: make a real test & remove this
    public static void testEncryptDecrypt() throws GeneralSecurityException, IOException {
        String test = "encrypt me!";
        System.out.println("test = " + test);
        KeysetHandle publicKeySetHandle = EncryptionUtils.getPublicKey();
        HybridEncrypt hybridEncrypt = publicKeySetHandle.getPrimitive(HybridEncrypt.class);
        byte[] testBytes = test.getBytes(StandardCharsets.UTF_8);
        System.out.println("testBytes = " + Arrays.toString(testBytes));
        String encryptedTest = new String(hybridEncrypt.encrypt(testBytes, "".getBytes(StandardCharsets.UTF_8)));
        System.out.println("encryptedTest = " + encryptedTest);
        String decryptedTest = EncryptionUtils.decrypt(encryptedTest);
        System.out.println("decryptedTest = " + decryptedTest);
    }

    public static void registerEncryptionConfig() throws IOException, GeneralSecurityException {
        HybridConfig.register();
        if (!new File(filePath).exists()) {
            KeysetHandle privateKeySetHandle = KeysetHandle.generateNew(
                    KeyTemplates.get("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM"));
            CleartextKeysetHandle.write(privateKeySetHandle,
                    JsonKeysetWriter.withFile(new File(filePath)));
        }
    }

    public static KeysetHandle getPublicKey() throws IOException, GeneralSecurityException {
        KeysetHandle privateKeySetHandle = CleartextKeysetHandle.read(
                JsonKeysetReader.withFile(new File(filePath)));
        return privateKeySetHandle.getPublicKeysetHandle();
    }

    public static String decrypt(String input) throws IOException, GeneralSecurityException {
        KeysetHandle privateKeySetHandle = CleartextKeysetHandle.read(
                JsonKeysetReader.withFile(new File(filePath)));
        HybridDecrypt hybridDecrypt = privateKeySetHandle.getPrimitive(HybridDecrypt.class);
        byte[] decrypted = hybridDecrypt.decrypt(input.getBytes(StandardCharsets.UTF_8), "".getBytes(StandardCharsets.UTF_8));
        return new String(decrypted, StandardCharsets.UTF_8);
    }
}

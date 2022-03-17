package com.example.code_block_server.auth;

import com.google.crypto.tink.*;
import com.google.crypto.tink.hybrid.HybridConfig;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

/*
 * Documentation:
 * https://github.com/google/tink/blob/master/docs/JAVA-HOWTO.md
 * https://developers.google.com/tink/hybrid
 */

public class EncryptionUtils {

    private static final String filePath = "./src/main/resources/key-set.json";

    public static Boolean keySetFileExists() {
        return new File(filePath).exists();
    }

    public static void generateKeySet() throws GeneralSecurityException, IOException {
        HybridConfig.register();
        // Generate the private key material
        KeysetHandle privateKeySetHandle = KeysetHandle.generateNew(
                KeyTemplates.get("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM"));
        CleartextKeysetHandle.write(privateKeySetHandle,
                JsonKeysetWriter.withFile(new File(filePath)));
    }

    public static KeysetHandle getPublicKey() throws IOException, GeneralSecurityException {
        KeysetHandle privateKeySetHandle = CleartextKeysetHandle.read(
                JsonKeysetReader.withFile(new File(filePath)));
        return privateKeySetHandle.getPublicKeysetHandle();
    }
}

package com.example.code_block_server.auth;

import com.example.code_block_server.dto.PublicKeyDTO;
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

    public static void registerEncryptionConfig() throws IOException, GeneralSecurityException {
        HybridConfig.register();
        if (!new File(filePath).exists()) {
            KeysetHandle privateKeySetHandle = KeysetHandle.generateNew(
                    KeyTemplates.get("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM"));
            CleartextKeysetHandle.write(privateKeySetHandle,
                    JsonKeysetWriter.withFile(new File(filePath)));
        }
    }

    public static PublicKeyDTO getPublicKey() throws IOException, GeneralSecurityException {
        KeysetHandle privateKeySetHandle = CleartextKeysetHandle.read(
                JsonKeysetReader.withFile(new File(filePath)));
        return  new PublicKeyDTO(privateKeySetHandle.getPublicKeysetHandle());
    }

    public static String decrypt(byte[] input) throws IOException, GeneralSecurityException {
        KeysetHandle privateKeySetHandle = CleartextKeysetHandle.read(
                JsonKeysetReader.withFile(new File(filePath)));
        HybridDecrypt hybridDecrypt = privateKeySetHandle.getPrimitive(HybridDecrypt.class);
        byte[] decrypted = hybridDecrypt.decrypt(input, null);
        return new String(decrypted);
    }
}

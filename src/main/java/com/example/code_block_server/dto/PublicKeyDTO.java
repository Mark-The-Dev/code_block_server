package com.example.code_block_server.dto;

import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.JsonKeysetWriter;
import com.google.crypto.tink.KeysetHandle;
import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Data
public class PublicKeyDTO {

    private byte[] encodedPublicKeySetHandle;

    public PublicKeyDTO(KeysetHandle publicKeySetHandle) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        CleartextKeysetHandle.write(publicKeySetHandle, JsonKeysetWriter.withOutputStream(os));
        encodedPublicKeySetHandle = os.toByteArray();
    }
}

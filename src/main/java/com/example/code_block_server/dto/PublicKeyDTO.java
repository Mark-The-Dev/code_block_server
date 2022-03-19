package com.example.code_block_server.dto;

import com.google.crypto.tink.KeysetHandle;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PublicKeyDTO {
    KeysetHandle publicKeySetHandle;
}

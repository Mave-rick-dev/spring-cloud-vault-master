package com.maverick.util;

import org.springframework.vault.VaultException;
import org.springframework.vault.support.VaultResponse;
import org.springframework.vault.support.VaultResponseSupport;

import java.util.Objects;

public class CommonUtils {
    public static <T> void checkNull(T response) {
        if (Objects.isNull(response) || (response instanceof VaultResponseSupport && Objects.isNull(((VaultResponseSupport<?>) response).getData()))
                || (response instanceof VaultResponse && Objects.isNull(((VaultResponse) response).getData()))) {
            throw new VaultException("Requested secret not found!!");
        }
    }
}

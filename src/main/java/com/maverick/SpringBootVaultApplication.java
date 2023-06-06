package com.maverick;

import com.google.gson.Gson;
import com.maverick.dto.PersonalCreds;
import com.maverick.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultSysOperations;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.core.VaultTransitOperations;
import org.springframework.vault.support.VaultMount;
import org.springframework.vault.support.VaultResponse;
import org.springframework.vault.support.VaultResponseSupport;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class SpringBootVaultApplication implements CommandLineRunner {

	// vaultTemplate might be underlined with red line, its IDE error but the project'll run regardless
	private final VaultTemplate vaultTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootVaultApplication.class, args);
	}

	@Override
	public void run(String... args) {

		String secretEnginePath = "dbcredentials";
		String secretPath = "spring-vault-demo";
		String secretKey = "example.username";

		VaultResponse response = vaultTemplate
				.opsForKeyValue(secretEnginePath, VaultKeyValueOperationsSupport.KeyValueBackend.KV_1)
				.get(secretPath);
		CommonUtils.checkNull(response);
		log.info("Vault Response: {}", new Gson().toJson(response.getData()));


		VaultResponseSupport<PersonalCreds> mappedCreds = vaultTemplate.read("secret/dbcredentials", PersonalCreds.class);
		CommonUtils.checkNull(mappedCreds);
		log.info("Mapped Credentials: {}", new Gson().toJson(mappedCreds.getData()));


		log.info("Requested secret: Key= {}, Value={}", secretKey, response.getData().get(secretKey));
		log.info("-------------------------------");

		//----------------------------------------------------------------

		String transitPath = "transit/";
		String storageEngineType = "transit";
		String encryptionKeyName = "foo-key";
		String dummyPlainText = "Secure message";


		// Encrypts data using the Transit backend.

		VaultTransitOperations transitOperations = vaultTemplate.opsForTransit();

		VaultSysOperations sysOperations = vaultTemplate.opsForSys();


		if (!sysOperations.getMounts().containsKey(transitPath)) {

			sysOperations.mount(storageEngineType, VaultMount.create(storageEngineType));

			transitOperations.createKey(encryptionKeyName);
		}

		// Encrypt a plain-text value
		String ciphertext = transitOperations.encrypt(encryptionKeyName, dummyPlainText);

		log.info("Encrypted value: {}", ciphertext);

		// Decrypt the cipher text
		String plaintext = transitOperations.decrypt(encryptionKeyName, ciphertext);

		log.info("Decrypted value: {}", plaintext);
		log.info("-------------------------------");
	}
}

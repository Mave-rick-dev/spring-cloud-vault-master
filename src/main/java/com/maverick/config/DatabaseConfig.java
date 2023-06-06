/*
package com.maverick.config;

import com.maverick.model.DatabaseCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.vault.VaultException;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {

    private final VaultTemplate vaultTemplate;

    @Bean
    public DataSource dataSource() {
        // Retrieve database credentials from Vault
//        VaultResponseSupport<DatabaseCredentials> response = vaultTemplate
//                .read("spring-vault-demo", DatabaseCredentials.class);
        VaultResponseSupport<DatabaseCredentials> response = vaultTemplate
                .read("secret/spring-vault-demo", DatabaseCredentials.class);

        if (Objects.isNull(response) || Objects.isNull(response.getData()))
            throw new VaultException("Request secret not found!!");

        DatabaseCredentials credentials = response.getData();

        // Configure the DataSource using the retrieved credentials
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(credentials.getUrl());
        dataSource.setUsername(credentials.getUsername());
        dataSource.setPassword(credentials.getPassword());
        dataSource.setDriverClassName("org.postgresql.Driver");

        return dataSource;
    }

}
*/

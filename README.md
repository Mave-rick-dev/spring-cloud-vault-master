# spring-cloud-vault-master
Demo project for HasiCorp Vault with Spring Cloud. 

# Terminal Commands:

- Run the server
vault server -dev
	- with dev root token id  
	vault server -dev --dev-root-token-id="00000000-0000-0000-0000-000000000000"
	- with custom config file (.hcl)  
	vault server -config=path\of\vaultHCLfile\vault.hcl

- Export the two env for Vault  
 export VAULT_ADDR='http://127.0.0.1:8200'  
 export VAULT_TOKEN="hvs.6j4cuewowBGit65rheNoceI7" (Optional)

- check the status of the vault  
vault status

- login into vault system from terminal  
vault login

- initialize the vault   
vault operator init

- seal it manually  
vault operator seal 

- unseal it manually  
vault operator unseal **unseal-key**

- create a new secret (key-value)  
vault secrets enable -path=dbcredentials/ kv

- add new key-value secred in new path 'dbcredentials'  
vault kv put dbcredentials/spring-vault-demo data='{ "dbusername": "postgres", "dbpassword": "password@1234"}'  
vault kv put dbcredentials/spring-vault-demo example.username=postgres example.password=password@1234

- get the secrets from the secret engine's path  
vault kv get dbcredentials/spring-vault-demo

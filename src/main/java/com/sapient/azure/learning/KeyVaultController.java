package com.sapient.azure.learning;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/az204")
public class KeyVaultController {

    @Autowired
    SecretClient client;

    private static final Logger LOG = LoggerFactory.getLogger(KeyVaultController.class);

    @GetMapping("/test/{secretKey}")
    public String getSecretKey(@PathVariable("secretKey") String secretKey){

        LOG.info("My Secret Value is "+secretKey);
        return secretKey;

    }

    @GetMapping("/secret/{secretKey}")
    public String getSecret(@PathVariable("secretKey") String secretKey){

        KeyVaultSecret secret = client.getSecret(secretKey);
        String secretValue = secret.getValue();
        LOG.info("My Secret Value is "+secretValue);
        return secretValue;

    }

}

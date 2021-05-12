package uk.fizjoterapia.gae;

import uk.fizjoterapia.gae.services.components.CloudStorageProperties;

public class AllMySecretsImpl implements AllMySecrets {

    final static AllMySecrets INSTANCE = new AllMySecretsImpl();
    private static CloudStorageProperties properties = null;

    private static CloudStorageProperties getProperties() {
        if (properties != null) return properties;
        properties = new CloudStorageProperties("config.properties");
        return properties;
    }

    @Override
    public String sendGrid() {
        return getProperties().getProperty("send-grid-api-key");
    }

}

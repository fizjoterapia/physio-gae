package uk.fizjoterapia.gae.services.components;

import com.google.cloud.storage.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

public class CloudStorageProperties {

    private static Storage storage = null;
    private final Properties properties = new Properties();

    private static void init() {
        synchronized (CloudStorageProperties.class) {
            if (storage == null) {
                storage = StorageOptions.getDefaultInstance().getService();
            }
        }
    }

    public CloudStorageProperties(String name) {
        init();

        final Blob blob = storage.get(BlobId.of("physio-effect.appspot.com", name));
        final byte[] content = blob.getContent();
        try {
            properties.load(new ByteArrayInputStream(content));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}

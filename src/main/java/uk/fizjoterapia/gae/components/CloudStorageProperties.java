package uk.fizjoterapia.gae.components;

import com.google.cloud.storage.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class CloudStorageProperties {

    private static final Map<String, CloudStorageProperties> MAP = new ConcurrentHashMap<>();
    private final Properties properties = new Properties();

    private CloudStorageProperties(String name) {
        Storage storage = GoogleServices.getStorage();
        final Blob blob = storage.get(BlobId.of("physio-effect.appspot.com", name));
        final byte[] content = blob.getContent();
        try {
            properties.load(new ByteArrayInputStream(content));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties getProperties() {
        return properties;
    }

    public static String getProperty(String name, String key) {
        if (!MAP.containsKey(name)) {
            synchronized (MAP) {
                if (!MAP.containsKey(name)) {
                    MAP.put(name, new CloudStorageProperties(name));
                }
            }
        }

        CloudStorageProperties cloudStorageProperties = MAP.get(name);
        return cloudStorageProperties.getProperties().getProperty(key);
    }

}

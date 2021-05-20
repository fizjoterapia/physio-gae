package uk.fizjoterapia.gae.components;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.util.concurrent.locks.ReentrantLock;

public class GoogleServices {

    private static final ReentrantLock storageLock = new ReentrantLock();
    private volatile static Storage storage = null;

    public static Storage getStorage() {
        if (storage != null) return storage;

        storageLock.lock();
        try {
            if (storage != null) return storage;
            storage = StorageOptions.getDefaultInstance().getService();
            return storage;
        } finally {
            storageLock.unlock();
        }
    }
}

package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("" + i, "fullName" + i));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume("" + AbstractArrayStorage.STORAGE_LIMIT + 1, "fullName" + AbstractArrayStorage.STORAGE_LIMIT + 1));
    }
}
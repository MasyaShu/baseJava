package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.*;

public abstract class AbstractArrayStorageTest {

    protected static Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.save(new Resume("1"));
        storage.save(new Resume("2"));
        storage.save(new Resume("3"));
    }

    @Test
    public void save() {
        storage.save(new Resume("4"));
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume("1"));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("" + i));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume("" + AbstractArrayStorage.STORAGE_LIMIT + 1));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void update() {
        Resume newResume = new Resume("2");
        storage.update(newResume);
        Resume storageResume = storage.get("2");
        Assert.assertEquals(newResume, storageResume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void get() {
        Assert.assertEquals("1", storage.get("1").getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test()
    public void delete() {
        storage.delete("1");
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void getAll() {
        Resume[] all = storage.getAll();
        Assert.assertEquals(all.length, storage.size());
    }
}

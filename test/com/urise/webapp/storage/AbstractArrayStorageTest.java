package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.*;

public abstract class AbstractArrayStorageTest {

    private Storage storage;
    private String uuid1 = "1";
    private String uuid2 = "2";
    private String uuid3 = "3";
    private String uuid4 = "4";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.save(new Resume(uuid1));
        storage.save(new Resume(uuid2));
        storage.save(new Resume(uuid3));
    }

    @Test
    public void save() {
        storage.save(new Resume(uuid4));
        storageSizeAssert(4);
        Assert.assertEquals(new Resume(uuid4), storage.get(uuid4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(uuid1));
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
        storageSizeAssert(0);
    }

    @Test
    public void size() {
        storageSizeAssert(3);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(uuid2);
        storage.update(newResume);
        Resume storageResume = storage.get(uuid2);
        Assert.assertEquals(newResume, storageResume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume(uuid1), storage.get(uuid1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(uuid1);
        storageSizeAssert(2);
        storage.get(uuid1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void getAll() {
        Resume[] all = storage.getAll();
        storageSizeAssert(3);
        Assert.assertEquals(all[0], storage.get(uuid1));
        Assert.assertEquals(all[1], storage.get(uuid2));
        Assert.assertEquals(all[2], storage.get(uuid3));
    }

    private void storageSizeAssert(int i) {
        Assert.assertEquals(i, storage.size());
    }
}

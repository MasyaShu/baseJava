package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private String uuid1 = "1";
    private String uuid2 = "2";
    private String uuid3 = "3";
    private String uuid4 = "4";
    private String fullName1 = "Ivan Sh";
    private String fullName2 = "Mariia Sh";
    private String fullName3 = "Max Sh";
    private String fullName4 = "Shu Sh";
    private Resume r1 = new Resume(uuid1, fullName1);
    private Resume r2 = new Resume(uuid2, fullName2);
    private Resume r3 = new Resume(uuid3, fullName3);
    private Resume r4 = new Resume(uuid4, fullName4);


    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void save() {
        storage.save(r4);
        assertStorageSize(4);
        Assert.assertEquals(r4, storage.get(uuid4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(r1);
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

    @Test
    public void clear() {
        storage.clear();
        assertStorageSize(0);
    }

    @Test
    public void size() {
        assertStorageSize(3);
    }

    @Test
    public void update() {
        Resume newResume = r2;
        storage.update(newResume);
        Resume storageResume = storage.get(uuid2);
        Assert.assertEquals(newResume, storageResume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(r4);
    }

    @Test
    public void get() {
        Assert.assertEquals(r1, storage.get(uuid1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(uuid4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(uuid1);
        assertStorageSize(2);
        storage.get(uuid1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(uuid4);
    }

    @Test
    public void getAll() {
        List<Resume> all = storage.getAllSorted();
        assertStorageSize(3);
        Assert.assertEquals(all.get(0), storage.get(uuid1));
        Assert.assertEquals(all.get(1), storage.get(uuid2));
        Assert.assertEquals(all.get(2), storage.get(uuid3));
    }

    private void assertStorageSize(int i) {
        Assert.assertEquals(i, storage.size());
    }
}
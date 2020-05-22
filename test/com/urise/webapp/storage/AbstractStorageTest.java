package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {

    protected Storage storage;
    private String uuid1 = "1";
    private String uuid2 = "2";
    private String uuid3 = "3";
    private String uuid4 = "4";
    private Resume r1 = new Resume(uuid1, "Ivan Sh");
    private Resume r2 = new Resume(uuid2, "Mariia Sh");
    private Resume r3 = new Resume(uuid3, "Max Sh");
    private Resume r4 = new Resume(uuid4, "Shu Sh");


    public AbstractStorageTest(Storage storage) {
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
        List<Resume> actualResumes = Arrays.asList(r1, r2, r3);
        assertStorageSize(3);
        Assert.assertEquals(actualResumes, all);
    }

    private void assertStorageSize(int i) {
        Assert.assertEquals(i, storage.size());
    }
}
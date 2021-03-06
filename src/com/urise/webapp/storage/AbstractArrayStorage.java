package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    abstract void saveResume(Resume resume, Integer key);

    abstract void deleteResume(int index);

    public void runSave(Resume resume, Integer key) {
        if (size == storage.length) {
            throw new StorageException("База переполнена, запись не возможна", resume.getUuid());
        } else {
            saveResume(resume, (Integer) key);
            size++;
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }


    public void runUpdate(Integer key, Resume resume) {
        storage[key] = resume;
    }

    public Resume runGet(Integer key) {
        Integer index = key;
        return storage[index];
    }

    public void runDelete(Integer key) {
        Integer index = key;
        storage[index] = null;
        if (size != index + 1) {
            deleteResume(index);
            storage[size - 1] = null;
        }
        size--;
    }

    public boolean isExist(Integer key) {
        return key >= 0;
    }

    public List<Resume> runGetAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }
}

package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    abstract void saveResume(Resume resume);

    abstract void deleteResume(int index);

    public void runSave(Resume resume) {
        if (size == storage.length) {
            throw new StorageException("База переполнена, запись не возможна", resume.getUuid());
        } else {
            saveResume(resume);
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


    public void runUpdate(int index, Resume resume) {
        storage[index] = resume;
    }

    public Resume runGet(int index) {
        return storage[index];
    }

    public void runDelete(int index) {
        storage[index] = null;
        if (size != index + 1) {
            deleteResume(index);
            storage[size - 1] = null;
        }
        size--;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }


}

package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int getIndex(String uuid);

    protected abstract void runSave(Resume resume, int index);

    protected abstract Resume runGet(int index);

    protected abstract void runUpdate(int index, Resume resume);

    protected abstract void runDelete(int index);

    public void save(Resume resume) {
        int index = indexNotExist(resume.getUuid());
        if (index < 0) {
            runSave(resume, index);
        }
    }

    public void update(Resume resume) {
        int index = indexExist(resume.getUuid());
        if (index >= 0) {
            runUpdate(index, resume);
        }
    }

    public Resume get(String uuid) {
        int index = indexExist(uuid);
        if (index >= 0) {
            return runGet(index);
        }
        return null;
    }

    public void delete(String uuid) {
        int index = indexExist(uuid);
        if (index >= 0) {
            runDelete(index);
        }
    }

    private int indexNotExist(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            return index;
        }
        throw new ExistStorageException(uuid);
    }

    private int indexExist(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return index;
        }
        throw new NotExistStorageException(uuid);
    }


}

package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object searchKey(String uuid);

    protected abstract void runSave(Resume resume, Object Key);

    protected abstract Resume runGet(Object index);

    protected abstract void runUpdate(Object index, Resume resume);

    protected abstract void runDelete(Object index);

    protected abstract boolean isExist(Object key);

    public void save(Resume resume) {
        Object key = searchKey(resume.getUuid());
        if (keyNotExist(key, resume.getUuid())) {
            runSave(resume, key);
        }
    }

    public void update(Resume resume) {
        Object key = searchKey(resume.getUuid());
        if (keyExist(key, resume.getUuid())) {
            runUpdate(key, resume);
        }
    }

    public Resume get(String uuid) {
        Object key = searchKey(uuid);
        if (keyExist(key, uuid)) {
            return runGet(key);
        }
        return null;
    }

    public void delete(String uuid) {
        Object key = searchKey(uuid);
        if (keyExist(key, uuid)) {
            runDelete(key);
        }

    }

    private boolean keyNotExist(Object key, String uuid) {
        if (!isExist(key)) {
            return true;
        }
        throw new ExistStorageException(uuid);
    }

    private boolean keyExist(Object key, String uuid) {
        if (isExist(key)) {
            return true;
        }
        throw new NotExistStorageException(uuid);
    }
}

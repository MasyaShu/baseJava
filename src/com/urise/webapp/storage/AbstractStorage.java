package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object searchKey(String uuid);

    protected abstract void runSave(Resume resume, Object key);

    protected abstract Resume runGet(Object key);

    protected abstract void runUpdate(Object key, Resume resume);

    protected abstract void runDelete(Object key);

    protected abstract boolean isExist(Object key);

    public void save(Resume resume) {
        Object key = KeyNotExist(resume.getUuid());
        runSave(resume, key);
    }

    public void update(Resume resume) {
        Object key = KeyExist(resume.getUuid());
        runUpdate(key, resume);
    }

    public Resume get(String uuid) {
        Object key = KeyExist(uuid);
        return runGet(key);
    }

    public void delete(String uuid) {
        Object key = KeyExist(uuid);
        runDelete(key);
    }

    private Object KeyExist(String uuid) {
        Object key = searchKey(uuid);
        if (isKeyExist(key, uuid)) {
            return key;
        }
        return null;
    }

    private Object KeyNotExist(String uuid) {
        Object key = searchKey(uuid);
        if (isKeyNotExist(key, uuid)) {
            return key;
        }
        return null;
    }

    private boolean isKeyNotExist(Object key, String uuid) {
        if (!isExist(key)) {
            return true;
        }
        throw new ExistStorageException(uuid);
    }

    private boolean isKeyExist(Object key, String uuid) {
        if (isExist(key)) {
            return true;
        }
        throw new NotExistStorageException(uuid);
    }
}

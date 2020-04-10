package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int getIndex(String uuid);

    protected abstract void runSave(Resume resume);

    protected abstract Resume runGet(int index);

    protected abstract void runUpdate(int index, Resume resume);

    protected abstract void runDelete(int index);

    public void save(Resume resume) {
        if (isNotExist(resume.getUuid())) {
            runSave(resume);
        }
    }

    public void update(Resume resume) {
        if(isExist(resume.getUuid())) {
            runUpdate(getIndex(resume.getUuid()), resume);
        }
    }

    public Resume get(String uuid) {
        if(isExist(uuid)) {
            return runGet(getIndex(uuid));
        }
        return null;
    }

    public void delete(String uuid) {
        if(isExist(uuid)) {
            runDelete(getIndex(uuid));
        }
    }

    private boolean isNotExist(String uuid) {
        if (getIndex(uuid) < 0) {
            return true;
        }
        throw new ExistStorageException(uuid);
    }

    private boolean isExist(String uuid) {
        if (getIndex(uuid) >= 0) {
            return true;
        }
        throw new NotExistStorageException(uuid);
    }


}

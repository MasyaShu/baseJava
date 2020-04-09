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
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            runUpdate(index, resume);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return runGet(index);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            runDelete(index);
        }
    }

    private boolean isNotExist(String uuid) {
        if (getIndex(uuid) < 0) {
            return true;
        }
        throw new ExistStorageException(uuid);
    }


}

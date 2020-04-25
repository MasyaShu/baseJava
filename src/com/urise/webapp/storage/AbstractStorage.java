package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract SK searchKey(String uuid);

    protected abstract void runSave(Resume resume, SK key);

    protected abstract Resume runGet(SK key);

    protected abstract void runUpdate(SK key, Resume resume);

    protected abstract void runDelete(SK key);

    protected abstract List<Resume> runGetAll();

    protected abstract boolean isExist(SK key);

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SK key = KeyNotExist(resume.getUuid());
        runSave(resume, key);
    }

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        SK key = KeyExist(resume.getUuid());
        runUpdate(key, resume);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK key = KeyExist(uuid);
        return runGet(key);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK key = KeyExist(uuid);
        runDelete(key);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("Get all sorted" );
        List<Resume> allStorage = runGetAll();
        Collections.sort(allStorage);
        return allStorage;
    }

    private SK KeyExist(String uuid) {
        SK key = searchKey(uuid);
        if (isExist(key)) {
            return key;
        }
        LOG.warning("Resume " + uuid + " not exist");
        throw new NotExistStorageException(uuid);
    }

    private SK KeyNotExist(String uuid) {
        SK key = searchKey(uuid);
        if (!isExist(key)) {
            return key;
        }
        LOG.warning("Resume " + uuid + " already exist");
        throw new ExistStorageException(uuid);
    }
}

package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    public Resume searchKey(String uuid) {
        return storage.get(uuid);
    }

    public void runSave(Resume resume, Object key) {
        storage.put(resume.getUuid(), resume);
    }

    public Resume runGet(Object key) {
        return storage.get(((Resume) key).getUuid());
    }

    public void runUpdate(Object key, Resume resume) {
        storage.replace(((Resume) key).getUuid(), resume);
    }

    public void runDelete(Object key) {
        storage.remove(((Resume) key).getUuid());
    }

    public List<Resume> runGetAll() {
        return new ArrayList<Resume>(storage.values());
    }

    protected boolean isExist(Object key) {
        return key != null;
    }

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }
}

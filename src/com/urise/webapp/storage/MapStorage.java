package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    public void runSave(Resume resume, Object key) {
        storage.put(resume.getUuid(), resume);
    }

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }

    public void runUpdate(Object key, Resume resume) {
        storage.replace((String) key, resume);
    }

    protected boolean isExist(Object key) {
        return key != null;
    }

    public Resume runGet(Object key) {
        return storage.get((String) key);
    }

    public void runDelete(Object key) {
        storage.remove((String) key);
    }

    public Resume[] getAll() {
        return storage.values().toArray(new Resume[storage.size()]);
    }

    protected String searchKey(String uuid) {
        if (storage.containsKey(uuid)) {
            return uuid;
        }
        return null;
    }
}

package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    protected String searchKey(String uuid) {
        if (storage.containsKey(uuid)) {
            return uuid;
        }
        return null;
    }

    public void runSave(Resume resume, Object key) {
        storage.put(resume.getUuid(), resume);
    }

    public Resume runGet(Object key) {
        return storage.get((String) key);
    }

    public void runUpdate(Object key, Resume resume) {
        storage.replace((String) key, resume);
    }

    public void runDelete(Object key) {
        storage.remove((String) key);
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

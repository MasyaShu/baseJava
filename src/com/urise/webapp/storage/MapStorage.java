package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {
    private Map<String, Resume> storage = new HashMap<>();

    protected String searchKey(String uuid) {
        if (storage.containsKey(uuid)) {
            return uuid;
        }
        return null;
    }

    public void runSave(Resume resume, String key) {
        storage.put(resume.getUuid(), resume);
    }

    public Resume runGet(String key) {
        return storage.get(key);
    }

    public void runUpdate(String key, Resume resume) {
        storage.replace(key, resume);
    }

    public void runDelete(String key) {
        storage.remove(key);
    }

    public List<Resume> runGetAll() {
        return new ArrayList<Resume>(storage.values());
    }

    protected boolean isExist(String key) {
        return key != null;
    }

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }
}

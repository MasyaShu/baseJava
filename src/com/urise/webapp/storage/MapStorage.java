package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {
    private HashMap<String, Resume> storage = new HashMap<>();

    public void runSave(Resume resume, int index) {
        storage.put(resume.getUuid(), resume);
    }

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }

    public void runUpdate(int index, Resume resume) {
        storage.replace(resume.getUuid(), resume);
    }

    public Resume runGet(int index, String uuid) {
        return storage.get(uuid);
    }

    public void runDelete(int index, String uuid) {
        storage.remove(uuid);
    }

    public Resume[] getAll() {
        return storage.values().toArray(new Resume[storage.size()]);
    }

    protected int getIndex(String uuid) {
        if(storage.containsKey(uuid)) {
            return 0;
        }
        return -1;
    }
}

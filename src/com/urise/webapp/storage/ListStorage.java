package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private ArrayList<Resume> storage = new ArrayList<>();

    protected Integer searchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void runSave(Resume resume, Object key) {
        storage.add(resume);
    }

    public Resume runGet(Object key) {
        return storage.get((Integer) key);
    }

    public void runUpdate(Object key, Resume resume) {
        storage.set((Integer) key, resume);
    }

    public void runDelete(Object key) {
        storage.remove(((Integer) key).intValue());
    }

    public List<Resume> runGetAll() {
        return storage;
    }

    public boolean isExist(Object key) {
        return (Integer) key >= 0;
    }

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }
}

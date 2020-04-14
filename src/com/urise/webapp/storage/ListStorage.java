package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    private ArrayList<Resume> storage = new ArrayList<>();

    public void runSave(Resume resume, Object key) {
        storage.add(resume);
    }

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }

    public void runUpdate(Object key, Resume resume) {
        storage.set((Integer) key, resume);
    }

    public Resume runGet(Object key) {
        return storage.get((Integer) key);
    }

    public void runDelete(Object key) {
        storage.remove(((Integer) key).intValue());
    }

    public boolean isExist(Object key) {
        return (Integer) key >= 0;
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    protected Integer searchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

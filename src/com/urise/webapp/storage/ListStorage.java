package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private ArrayList<Resume> storage = new ArrayList<>();

    protected Integer searchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void runSave(Resume resume, Integer key) {
        storage.add(resume);
    }

    public Resume runGet(Integer key) {
        return storage.get(key);
    }

    public void runUpdate(Integer key, Resume resume) {
        storage.set(key, resume);
    }

    public void runDelete(Integer key) {
        storage.remove(key.intValue());
    }

    public List<Resume> runGetAll() {
        return storage;
    }

    public boolean isExist(Integer key) {
        return key >= 0;
    }

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }
}

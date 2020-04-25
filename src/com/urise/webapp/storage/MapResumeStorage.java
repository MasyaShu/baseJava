package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private Map<String, Resume> storage = new HashMap<>();

    public Resume searchKey(String uuid) {
        return storage.get(uuid);
    }

    public void runSave(Resume resume, Resume key) {
        storage.put(resume.getUuid(), resume);
    }

    public Resume runGet(Resume key) {
        return storage.get(key.getUuid());
    }

    public void runUpdate(Resume key, Resume resume) {
        storage.replace(key.getUuid(), resume);
    }

    public void runDelete(Resume key) {
        storage.remove(key.getUuid());
    }

    public List<Resume> runGetAll() {
        return new ArrayList<Resume>(storage.values());
    }

    protected boolean isExist(Resume key) {
        return key != null;
    }

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }
}

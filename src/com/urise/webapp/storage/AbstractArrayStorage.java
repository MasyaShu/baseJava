package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public abstract int getIndex(String uuid);

    public abstract void save(Resume resume);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Резюме с УИД " + resume.getUuid() + " нет в базе");
        } else {
            storage[index] = resume;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            storage[index] = null;
            if (size != index + 1) {
                System.arraycopy(storage, index + 1, storage, index, size - index - 1);
                storage[size - 1] = null;
            }
            size--;
        } else {
            System.out.println("Резюме с УИД " + uuid + " нет в базе");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
}

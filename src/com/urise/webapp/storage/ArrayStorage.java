package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void saveResume(Resume resume, Integer key) {
        storage[size] = resume;
    }

    @Override
    public Integer searchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void deleteResume(int index) {
        storage[index] = storage[size - 1];
    }
}

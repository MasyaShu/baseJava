package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void saveResume(Resume resume, int index) {
        int insertIndex = -index - 1;
        if (insertIndex != 0) {
            System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        }
        storage[insertIndex] = resume;
    }

    @Override
    public void deleteResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    @Override
    public int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }
}

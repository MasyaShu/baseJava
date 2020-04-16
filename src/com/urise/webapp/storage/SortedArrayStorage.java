package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    public void saveResume(Resume resume, Integer index) {
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
    public Integer searchKey(String uuid) {
        Resume resume = new Resume(uuid, "Shu Sh");
        return Arrays.binarySearch(storage, 0, size, resume, RESUME_COMPARATOR);
    }
}

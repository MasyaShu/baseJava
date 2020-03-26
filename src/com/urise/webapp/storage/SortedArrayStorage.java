package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = Arrays.binarySearch(storage, 0, size, resume);
        if (size == storage.length) {
            System.out.println("База переполнена, запись не возможна>");
        } else if (index >= 0) {
            System.out.println("Резюме с УИД " + resume.getUuid() + " уже есть в базе");
        } else {
            int insertIndex = -index - 1;
            if (insertIndex != 0) {
                System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
            }
            storage[insertIndex] = resume;
            size++;
        }
    }

    @Override
    public int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }
}

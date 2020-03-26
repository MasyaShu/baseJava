package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        if(size == storage.length) {
            System.out.println("База переполнена, запись не возможна>");
        } else if(getIndex(resume.getUuid()) >= 0) {
            System.out.println("Резюме с УИД " + resume.getUuid() + " уже есть в базе");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    @Override
    public int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}

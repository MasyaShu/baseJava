package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if(getPositionResume(resume.getUuid()) != 0) {
            System.out.println("Резюме с таким УИД уже существует");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void update(Resume resume) {
        if(getPositionResume(resume.getUuid()) == 0) {
            System.out.println("Резюме с таким УИД нет");
        } else {
            storage[getPositionResume(resume.getUuid()) -1] = resume;
        }
    }

    public Resume get(String uuid) {
        int i = getPositionResume(uuid);
        if(i != 0) {
            return storage[i];
        }
        System.out.println("Резюме с таким УИД нет");
        return null;
    }

    public void delete(String uuid) {
       int i = getPositionResume(uuid);
       if(i != 0) {
           System.arraycopy(storage, i, storage, i - 1, size - i);
           size--;
       } else {
           System.out.println("Резюме с таким УИД нет");
       }
    }

     private int getPositionResume(String uuid) {
        int i;
        for(i = 0; i < size; i++) {
            if(uuid.equals(storage[i].getUuid())) {
                return i + 1;
            }
        }
        return 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}

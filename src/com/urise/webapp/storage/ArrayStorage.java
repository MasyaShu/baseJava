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
        if(getIndexResume(resume.getUuid()) >= 0) {
            System.out.println("Резюме с УИД " + resume.getUuid() + " уже есть в базе");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void update(Resume resume) {
        int index = getIndexResume(resume.getUuid());
        if(index < 0) {
            System.out.println("Резюме с УИД "+ resume.getUuid()  + " нет в базе");
        } else {
            storage[index] = resume;
        }
    }

    public Resume get(String uuid) {
        int index = getIndexResume(uuid);
        if(index  >= 0) {
            return storage[index];
        }
        System.out.println("Резюме с УИД " + uuid + " нет в базе");
        return null;
    }

    public void delete(String uuid) {
       int index = getIndexResume(uuid);
       if(index  >= 0) {
           System.arraycopy(storage, index + 1, storage, index, size - index);
           size--;
       } else {
           System.out.println("Резюме с УИД " + uuid + " нет в базе");
       }
    }

     private int getIndexResume(String uuid) {
        for(int i = 0; i < size; i++) {
            if(uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
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

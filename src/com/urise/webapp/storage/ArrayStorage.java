package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size + 1, null);
        size = 0;
    }

    public void save(Resume r) {
        if(isPresent(r.getUuid())) {
            System.out.println("Резюме с таким УИД уже существует");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        if(!isPresent(r.getUuid())) {
            System.out.println("Резюме с таким УИД нет");
        } else {
            storage[getIndexResume(r.getUuid())] = r;
        }
    }

    public Resume get(String uuid) {
        if(isPresent(uuid)) {
            return storage[getIndexResume(uuid)];
        }
        else {
            System.out.println("Резюме с таким УИД нет");
            return null;
        }
    }

    public void delete(String uuid) {
       if(isPresent(uuid)) {
           for(int i = getIndexResume(uuid) + 1; i <= size; i++) {
               storage[i - 1] = storage[i];
           }
           size--;
       } else {
           System.out.println("Резюме с таким УИД нет");
       }
    }

    private boolean isPresent(String uuid) {
        for(int i = 0; i < size; i++) {
            if(storage[i].getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    private int getIndexResume(String uuid) {
        int i;
        for(i = 0; i < size; i++) {
            if(uuid.equals(storage[i].getUuid())) {
                break;
            }
        }
        return i;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        int size = size();
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}

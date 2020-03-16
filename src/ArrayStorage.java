import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for(int i = 0; i < 10000; i++) {
            if(storage[i] == null) {
                Arrays.fill(storage, 0, i + 1, null);
                break;
            }
        }
    }

    void save(Resume r) {
        for(int i = 0; i < 10000; i++) {
            if(storage[i] == null) {
                storage[i] = r;
                break;
            } else if(storage[i].uuid.equals(r.uuid)) {
                System.out.println("Резюме с таким УИД уже существует");
                break;
            }
        }
    }

    Resume get(String uuid) {
        for(Resume r : storage) {
            if(r == null) {
                return null;
            } else {
                if(uuid.equals(r.uuid)) {
                    return r;
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        for(int i = 0; i < 10000; i++) {
            if(storage[i] == null) {
                break;
            } else {
                if(storage[i].uuid.equals(uuid)) {
                    Arrays.fill(storage, i, i + 1, null);
                    for(int y = i + 1; y < 10000; y++) {
                        if(storage[y] == null) {
                            break;
                        } else {
                            storage[y - 1] = storage[y];
                            storage[y] = null;
                        }
                    }
                    break;
                }
            }
        }
        //Arrays.sort(storage);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        for(int i = 0; i < 10000; i++) {
            if(storage[i] == null) {
                return Arrays.copyOf(storage, i);
            }
        }
        return Arrays.copyOf(storage, 9999);
    }

    int size() {
        for(int i = 0; i < 10000; i++) {
            if(storage[i] == null) {
                return i;
            }
        }
        return 0;
    }
}

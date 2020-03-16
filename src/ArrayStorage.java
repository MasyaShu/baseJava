import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int size = size();
        Arrays.fill(storage, 0, size + 1, null);
    }

    void save(Resume r) {
        int size = size();
        boolean isIncluded = false;

        for(int i = 0; i < size; i++) {
            if(storage[i].uuid.equals(r.uuid)) {
                System.out.println("Резюме с таким УИД уже существует");
                isIncluded = true;
                break;
            }
        }

        if(!isIncluded) {
            storage[size] =r;
        }
    }

    Resume get(String uuid) {
        int size = size();
        for(int i = 0; i < size; i++) {
            if(uuid.equals(storage[i].uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int size = size();
        for(int i = 0; i < size; i++) {
            if(storage[i].uuid.equals(uuid)) {
                Arrays.fill(storage, i, i + 1, null);
                for(int y = i + 1; y < size; y++) {
                        storage[y - 1] = storage[y];
                        storage[y] = null;
                }
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int size = size();
        return Arrays.copyOf(storage, size);
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

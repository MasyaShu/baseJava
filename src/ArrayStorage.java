import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        Arrays.fill(storage, 0, size + 1, null);
        size = 0;
    }

    void save(Resume r) {
        boolean isExists = false;

        for(int i = 0; i < size; i++) {
            if(storage[i].uuid.equals(r.uuid)) {
                System.out.println("Резюме с таким УИД уже существует");
                isExists = true;
                break;
            }
        }

        if(!isExists) {
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {
        for(int i = 0; i < size; i++) {
            if(uuid.equals(storage[i].uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for(int i = 0; i < size; i++) {
            if(storage[i].uuid.equals(uuid)) {
                for(int j = i + 1; j <= size; j++) {
                        storage[j - 1] = storage[j];
                }
                size--;
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
        return size;
    }
}

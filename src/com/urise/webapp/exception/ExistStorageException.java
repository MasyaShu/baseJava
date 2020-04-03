package com.urise.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Резюме с УИД " + uuid + " уже есть в базе", uuid);
    }
}

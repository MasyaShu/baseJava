package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class StringListSection extends AbstractSection<List<String>> {
    private List<String> info;

    public StringListSection(List<String> info) {
        Objects.requireNonNull(info, "info must not be null");
        this.info = info;
    }

    @Override
    public List<String> getInfo() {
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringListSection that = (StringListSection) o;
        return info.equals(that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info);
    }
}

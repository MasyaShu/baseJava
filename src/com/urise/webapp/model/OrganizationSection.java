package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection<List<Experience>> {
    private List<Experience> info;

    public OrganizationSection(List<Experience> organizations) {
        Objects.requireNonNull(organizations, "organizations must not be null");
        this.info = organizations;
    }

    @Override
    public List<Experience> getInfo() {
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return info.equals(that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info);
    }
}

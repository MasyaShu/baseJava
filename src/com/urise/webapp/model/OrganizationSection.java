package com.urise.webapp.model;

import java.util.List;

public class OrganizationSection extends AbstractSection<List<Experience>> {
    private List<Experience> info;

    public OrganizationSection(List<Experience> info) {
        this.info = info;
    }

    @Override
    public List<Experience> getInfo() {
        return info;
    }
}

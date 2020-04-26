package com.urise.webapp.model;

import java.util.List;

public class StringListSection extends AbstractSection<List<String>> {
    private List<String> info;

    public StringListSection(List<String> info) {
        this.info = info;
    }

    @Override
    public List<String> getInfo() {
        return info;
    }
}

package com.urise.webapp.model;

import java.util.List;

public class ListObjectSection extends Section<List<EduSection>> {
    private List<EduSection> info;

    public ListObjectSection(List<EduSection> info) {
        this.info = info;
    }

    @Override
    public List<EduSection> getInfo() {
        return info;
    }
}

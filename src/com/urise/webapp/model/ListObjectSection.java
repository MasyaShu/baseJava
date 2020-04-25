package com.urise.webapp.model;

import java.util.List;

public class ListObjectSection extends Section<EduSection> {
    private List<EduSection> info;

    public ListObjectSection(List<EduSection> info) {
        this.info = info;
    }
}

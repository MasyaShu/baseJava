package com.urise.webapp.model;

import java.util.List;

public class StringListSection extends Section<String> {
    private List<String> info;

    public StringListSection(List<String> info) {
        this.info = info;
    }
}

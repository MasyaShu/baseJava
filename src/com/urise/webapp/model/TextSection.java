package com.urise.webapp.model;

public class TextSection extends AbstractSection<String> {
    public String info;

    public TextSection(String info) {
        this.info = info;
    }

    @Override
    public String getInfo() {
        return info;
    }
}

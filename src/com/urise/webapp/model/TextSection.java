package com.urise.webapp.model;

public class TextSection extends Section<String> {
    public String info;

    public TextSection(String info) {
        this.info = info;
    }

    @Override
    public String getInfo() {
        return info;
    }
}

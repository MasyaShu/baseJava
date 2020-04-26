package com.urise.webapp.model;

import java.time.YearMonth;

public class Experience {
    YearMonth data;
    YearMonth dataTo;
    String establishment;
    String url;
    String position;

    public Experience(YearMonth data, YearMonth dataTo, String establishment, String http, String position) {
        this.data = data;
        this.dataTo = dataTo;
        this.establishment = establishment;
        this.url = http;
        this.position = position;
    }

    public YearMonth getData() {
        return data;
    }

    public YearMonth getDataTo() {
        return dataTo;
    }

    public String getEstablishment() {
        return establishment;
    }

    public String getPosition() {
        return position;
    }

    public String getUrl() {
        return url;
    }
}

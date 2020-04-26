package com.urise.webapp.model;

import java.time.YearMonth;

public class EduSection {
    YearMonth data;
    YearMonth dataTo;
    String establishment;
    String http;
    String position;

    public EduSection(YearMonth data, YearMonth dataTo, String establishment, String http,String position) {
        this.data = data;
        this.dataTo = dataTo;
        this.establishment = establishment;
        this.http = http;
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

    public String getHttp() {
        return http;
    }
}

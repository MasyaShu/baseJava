package com.urise.webapp.model;

import java.time.YearMonth;

public class EduSection {
    YearMonth data;
    YearMonth dataTo;
    String establishment;
    String position;

    public EduSection(YearMonth data, YearMonth dataTo, String establishment, String position) {
        this.data = data;
        this.dataTo = dataTo;
        this.establishment = establishment;
        this.position = position;
    }
}

package com.urise.webapp.model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Experience {
    private final Link url;
    private List<Position> positions = new ArrayList<>();

    public Experience(Link homePage, List<Position> positions) {
        this.url = homePage;
        this.positions = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return Objects.equals(url, that.url) &&
                Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, positions);
    }

    @Override
    public String toString() {
        return "Organization(" + url + "," + positions + ')';
    }

    public Link getUrl() {
        return url;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public static class Position {
        private final YearMonth data;
        private final YearMonth dataTo;
        private final String establishment;
        private final String position;

        public Position(YearMonth data, YearMonth dataTo, String establishment, String position) {
            this.data = data;
            this.dataTo = dataTo;
            this.establishment = establishment;
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
    }
}
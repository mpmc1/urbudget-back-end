package com.urbudget.apiyear.domain.year;

public class Year {

    private String id;
    private int year;

    public Year() {

    }

    public Year(String id, int year) {
        setId(id);
        setYear(year);
    }

    public static final Year create() {
        return new Year();
    }

    public final String getId() {

        return id.trim();
    }

    public final void setId(final String id) {
        this.id = id;
    }

    public final int getYear() {
        if (year < 0) {
            setYear(0);
        }
        return year;
    }

    public final void setYear(final int year) {
        this.year = year;
    }



}

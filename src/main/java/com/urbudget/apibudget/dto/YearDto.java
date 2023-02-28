package com.urbudget.apibudget.dto;

public class YearDto {

    private String id;
    private int year;

    public YearDto() {

    }

    public YearDto(String id, int year) {
        setId(id);
        setYear(year);
    }

    public static final YearDto create() {
        return new YearDto();
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

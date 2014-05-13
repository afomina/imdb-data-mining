package com.afomina.datamining.model;

public class Movie {

    private String name;
    private Integer year;
    private String info;

    public Movie() {}

    public Movie(String name) {
        this.name = name;
    }

    public Movie(String name, Integer year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", info='" + info + '\'' +
                '}';
    }
}

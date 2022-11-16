package org.example.entities;

public class Movie {
    @Primary
    private final int id;
    @Unique
    private String name;
    private Integer year;

    public Movie(int id, String name,Integer year) {
        this.id = id;
        this.name = name;
        this.year=year;
    }

    public Movie(int id) {
        this.id = id;
    }
}

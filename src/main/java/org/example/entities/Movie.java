package org.example.entities;

public class Movie {
    @Primary
    private final int id;
    @Unique
    private String name;

    public Movie(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Movie(int id) {
        this.id = id;
    }
}

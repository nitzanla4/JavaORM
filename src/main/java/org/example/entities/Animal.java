package org.example.entities;

import java.util.Objects;

public class Animal {
    int id;
    String name;
    int age;
    String habitat;

    public Animal() {}

    public Animal(int id, String name, int age, String habitat) {
        this.id = id;
        this.name = name;
        this.age=age;
        this.habitat = habitat;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", habitat='" + habitat + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id && age == animal.age && Objects.equals(name, animal.name) && Objects.equals(habitat, animal.habitat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, habitat);
    }
}

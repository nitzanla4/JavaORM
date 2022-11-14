package org.example;

public class Animal {
    int id;
    String name;
    int age;
    String habitat;

    public Animal(int id, String name,int age, String habitat) {
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
}

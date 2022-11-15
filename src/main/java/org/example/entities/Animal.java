package org.example.entities;

import java.util.Objects;

public class Animal {
    @Primary
    private final int id;
    @Unique
    private String name;
    private int age;
    private String habitat;

    public Animal(int id) {
        this.id = id;
    }
    public static class Builder{
        //Required Parameters
        private final int id;
        //Optional Parameters
        private String name=null;
        private int age=0;
        private String habitat=null;

        public Builder(int id){
            this.id=id;
        }
        public Builder name(String name){
            this.name=name;
            return this;
        }
        public Builder age(int age){
            this.age=age;
            return this;
        }
        public Builder habitat(String habitat){
            this.habitat=habitat;
            return this;
        }
        public Animal build(){
            return new Animal(this);
        }
    }

    private  Animal(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.age=builder.age;
        this.habitat = builder.habitat;
    }

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

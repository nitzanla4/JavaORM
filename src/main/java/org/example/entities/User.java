package org.example.entities;

import java.util.Objects;

public class User {
    @Primary
    private final int id;
    private String name;
    @Unique
    private String email;
    private String password;

    public static class Builder{
        //Required Parameters
        private final int id;
        //Optional Parameters
        private String name=null;
        private String email=null;
        private String password=null;




        public Builder(int id){
            this.id=id;
        }
        public Builder name(String name){
            this.name=name;
            return this;
        }
        public Builder email(String email){
            this.email=email;
            return this;
        }
        public Builder password(String password){
            this.password=password;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }

    private  User(Builder builder){
        this.id= builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
    }



    public User() {
        this.id =0;
    }

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password);
    }
}

package org.example;

import Repository.Repositiory;

import java.util.List;

public class Client {
    public static void main(String[] args) {
        Repositiory<User> userRepositiory= new Repositiory<>(User.class);
        List<User> users=userRepositiory.executeQuery();
        users.forEach(user-> System.out.println(user.toString()));
    }
}
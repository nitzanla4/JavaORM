package org.example;

import Repository.Repository;

import java.util.List;

public class Client {
    public static void main(String[] args) {
        Repository<User> userRepository= new Repository<>(User.class);
        List<User> users=userRepository.executeQuery();
        users.forEach(user-> System.out.println(user.toString()));
    }
}
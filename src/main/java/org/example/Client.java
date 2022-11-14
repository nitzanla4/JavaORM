package org.example;

import Repository.Repository;

import java.sql.SQLException;
import java.util.List;

public class Client {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Repository<User> userRepository= new Repository<>(User.class);
        List<User> users=userRepository.executeQuery();
        users.forEach(user-> System.out.println(user.toString()));
        System.out.println(userRepository.findOnebyId(2));



    }
}
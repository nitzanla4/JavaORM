package org.example;

import repository.Read;
import repository.Repository;

import java.sql.SQLException;
import java.util.List;

public class Client {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Repository<User> userRepository= new Repository<>(User.class);
        Read<User> read= new Read<>(User.class);
        List<User> users=read.findAll();

        users.forEach(user-> System.out.println(user.toString()));
        System.out.println(read.findOneById(2));



    }
}
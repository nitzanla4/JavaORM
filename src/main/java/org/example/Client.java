package org.example;

import repository.Add;
import repository.CreateTable;
import repository.Read;
import repository.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException {
        Repository<User> userRepository= new Repository<>(User.class);
        Read<User> read= new Read<>(User.class);
        List<User> users=read.findAll();

        users.forEach(user-> System.out.println(user.toString()));
        System.out.println(read.findOneById(2));
        System.out.println("---By property---------------");
        System.out.println(read.findByProperty("Hadi" , "name"));



    }
}
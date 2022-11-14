package org.example;

import repository.Add;
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
        //System.out.println(read.findOneByProperty("Hadi"));


        System.out.println("-------------add new user: ");
        Add<User> add= new Add<>(User.class);
        User yossi=new User(101,"Yossi","yossi@gmail.com","456789");
        add.addSingleItem(yossi);


        System.out.println("-------------add list of users: ");

        List<User> userList= new ArrayList<>();
        userList.add(new User(11,"Yaffa","yaffa@gmail.com","456789"));
        userList.add(new User(12,"Sarah","sarah@gmail.com","456789"));
        add.addMultipleItem(userList);

    }
}
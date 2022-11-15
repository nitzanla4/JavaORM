package org.example;

import org.example.entities.Animal;
import org.example.entities.User;
import repository.*;
import repository.queries.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException {
        Repository<User> userRepository = new Repository<>(User.class);
        Read<User> read = new Read<>(User.class);
        List<User> users = read.findAll();

//        users.forEach(user-> System.out.println(user.toString()));
//        System.out.println(read.findOneById(2));
//        System.out.println("---By property---------------");
//        System.out.println(read.findByProperty("Hadi" , "name"));


//        System.out.println("-------------add new user: ");
        Add<User> add = new Add<>(User.class);
        User yossi=new User(101,"Yossi","yossi@gmail.com","456789");
//        add.addSingleItem(yossi);
//
//        users.forEach(user-> System.out.println(user.toString()));

        System.out.println("-------------add list of users: ");
        List<User> userList = new ArrayList<>();
        userList.add(new User(115, "Yaffa", "yaffa@gmail.com", "456789"));
        userList.add(new User(116, "Yaffa", "sarah@gmail.com", "456789"));
//    add.addMultipleItem(userList);

        users.forEach(user -> System.out.println(user.toString()));

        System.out.println("--DELETE BY PROPERTY---------------");
        Delete<User> delete = new Delete<>(User.class);
        delete.deleteOneByProperty(15, "id");


        //System.out.println("----Multi DELETE-------------------------------------");
        //delete.deleteMultipleItem("Yaffa", "name");
        System.out.println("-------------create new table: ");
        /*CreateTable<User> createUserTable= new CreateTable<>(User.class);
        createUserTable.createNewTable(yossi);*/
//facade
        DBFacade<User> dbFacade= new DBFacade<>(User.class);
        dbFacade.add.addSingleItem(yossi);
        dbFacade.delete.deleteEntireTable("animal");

        CreateTable<Animal> createAnimalTable = new CreateTable<>(Animal.class);
        Animal dog = new Animal(1, "Dogi", 3, "Home");
        createAnimalTable.createNewTable(dog);

        Update<User> update = new Update<>(User.class);
        update.updateByProperty("Hadis", "name", 2);



    }
}


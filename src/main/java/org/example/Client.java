package org.example;

import org.example.entities.User;
import repository.DBFacade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException {

        DBFacade<User> dbFacade= new DBFacade<>(User.class);

        System.out.println("-------------add new user: ");
        User yossi=new User(350,"Yossi","yossi@gmail.com","456789");

        dbFacade.addSingleItem(yossi);


        System.out.println("-------------add list of users: ");
        List<User> userList = new ArrayList<>();
        userList.add(new User(351, "Yaffa", "yaffa@gmail.com", "456789"));
        userList.add(new User(352, "Yaffa", "sarah@gmail.com", "456789"));
        dbFacade.addMultipleItem(userList);


//        System.out.println("-------------find all: ");
//        dbFacade.read.findAll();
        System.out.println("-------------find one by id: ");
        System.out.println(dbFacade.findOneById(342));
        System.out.println("-------------find one by property: ");
        System.out.println(dbFacade.findByProperty("Hadi" , "name"));


        System.out.println("-------------delete by property: ");
        dbFacade.deleteOneByProperty(342, "id");

        System.out.println("-------------delete multiple item: ");
////        dbFacade.delete.deleteMultipleItem("Yaffa", "name");

//        System.out.println("-------------delete entire table: ");
//        dbFacade.delete.deleteEntireTable("animal");



//
//        System.out.println("-------------create new table: ");
//        dbFacade.createTable.createNewTable(yossi); //by User
//        Animal dog = new Animal(1, "Dogi", 3, "Home");
//        dbFacade.createTable.createNewTable(dog);
//
//
//        System.out.println("-------------update by property: ");
//        dbFacade.update.updateByProperty("Hadis", "name", 2);
//

    }
}
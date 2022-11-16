package org.example;

import org.example.entities.Animal;
import org.example.entities.Movie;
import org.example.entities.User;
import repository.DBFacade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {

        DBFacade<User> dbFacadeUser= new DBFacade<>(User.class);

        System.out.println("-------------add new user: ");
        User yossi = new User(360,"Yossi","yossi@gmail.com","456789");
//        dbFacadeUser.addSingleItem(yossi.getClass().getSimpleName(), yossi);


//        System.out.println("-------------add list of users: ");
//        List<User> userList = new ArrayList<>();
//        userList.add(new User(351, "Yaffa", "yaffa@gmail.com", "456789"));
//        userList.add(new User(352, "Yaffa", "sarah@gmail.com", "456789"));
//        dbFacadeUser.addMultipleItem(userList);


//        System.out.println("-------------find all: ");
//        dbFacadeUser.read.findAll();
        System.out.println("-------------find one by id: ");
        System.out.println(dbFacadeUser.readOneById(352));
        System.out.println("-------------find one by property: ");
        System.out.println(dbFacadeUser.readByProperty("Hadi" , "name"));


        System.out.println("-------------delete by property: ");
        dbFacadeUser.deleteOneByProperty(352, "id");

        System.out.println("-------------delete multiple item: ");
////        dbFacadeUser.delete.deleteMultipleItem("Yaffa", "name");

//        System.out.println("-------------delete entire table: ");
        //dbFacadeUser.deleteEntireTable("animal");


//
//        System.out.println("-------------create new table: ");
        //db to animal:
        DBFacade<Animal> dbFacadeAnimal= new DBFacade<>(Animal.class);
        Animal cat= new Animal.Builder(123).name("cati").habitat("zoo").age(10).build();
        Animal dog = new Animal(1, "Dogi", 3, "Home");
        dbFacadeAnimal.createNewTable(dog);

        //db to movie:
        DBFacade<Movie> dbFacadeMovie= new DBFacade<>(Movie.class);
        Movie movie = new Movie(1, "aaa",1999);
        dbFacadeMovie.createNewTable(movie);

//
        //dbFacadeUser.deleteEntireTable("animal");
//
        System.out.println("-------------update by property: ");
        dbFacadeUser.updateOneByProperty("name", "Hadis", "name", "Hadi");
//

    }
}
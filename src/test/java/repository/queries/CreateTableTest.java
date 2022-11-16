package repository.queries;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.entities.Movie;
import org.example.entities.User;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static repository.queries.CreateTable.typeValidation;

public class CreateTableTest {

    private static User user;

    @Test
    private static void typeValidation_Int_NotPrimitiveTest() {
        User user= new User.Builder(123).name("Tal").email("tal@gmail.com").password("121212").build();
        Field[] declaredFields = user.getClass().getDeclaredFields();
        String res=typeValidation(declaredFields[0]);//int

        assertTrue(res==int.class.toString(),    "suscces: int should stay int ");
    }

    @Test
    private static void typeValidation_String_NotPrimitiveTest() {
        User user= new User.Builder(123).name("Tal").email("tal@gmail.com").password("121212").build();
        Field[] declaredFields = user.getClass().getDeclaredFields();
        String res=typeValidation(declaredFields[1]);//string

        assertTrue(res=="longtext",    "success: string should casr to longtext");
    }
    @Test
    private static void typeValidation_Integer_NotPrimitiveTest() {
        Movie Titanic= new Movie(12,"Titanic",1990);
        Field[] declaredFields = Titanic.getClass().getDeclaredFields();
        String res=typeValidation(declaredFields[2]);//Integer

        assertTrue(res=="json",    "success: not primitive type cast to json");
    }

}
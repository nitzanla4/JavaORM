package repository.queries;

import org.example.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.DBFacade;
import repository.queries.ConnectionToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectionToDBTest {
        static String DBName = "mydb";

        @Test
        public void createTable_should_connect_to_db() throws SQLException {
            DBFacade<User> repo = new DBFacade<>(User.class);

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mysql://" + ConnectionToDB.getDotenv().get("HOST") + ":" +ConnectionToDB.getDotenv().get("PORT")
                    + "/" + ConnectionToDB.getDotenv().get("DB");
            Connection con = DriverManager.getConnection(url, ConnectionToDB.getDotenv().get("USER"), ConnectionToDB.getDotenv().get("PASSWORD"));

            Statement stmt = con.createStatement();
            Assertions.assertTrue(stmt != null, "The application has connection ");
        }



}



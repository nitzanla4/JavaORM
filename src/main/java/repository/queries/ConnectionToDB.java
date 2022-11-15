package repository.queries;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ConnectionToDB {
    private static Connection con = null;

    private static Dotenv dotenv = Dotenv.load();

    private static Logger logger = LogManager.getLogger(ConnectionToDB.class.getName());

    public static Dotenv getDotenv() {
        return dotenv;
    }


    public static Connection openConnectionToDB() {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mysql://" + dotenv.get("HOST") + ":" + dotenv.get("PORT") + "/" + dotenv.get("DB");
            con = DriverManager.getConnection(url, dotenv.get("USER"), dotenv.get("PASSWORD"));
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}



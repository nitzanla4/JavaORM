package repository.queries;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Repository<T> {
    static Class<?> clz;
    static Connection con = null;
    static Statement statement = null;
    static ResultSet resultSet = null;

    PreparedStatement preparedStatement = null;

    private static Dotenv dotenv = Dotenv.load();

    private static Logger logger = LogManager.getLogger(Repository.class.getName());


    public Repository(Class<T> clz) {
        this.clz = clz;
    }

    public static void openConnectionToDB() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://" + dotenv.get("HOST") + ":" + dotenv.get("PORT") + "/" + dotenv.get("DB");
        try {
            con = DriverManager.getConnection(url, dotenv.get("USER"), dotenv.get("PASSWORD"));
            statement = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static <T> T createObject() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        Constructor<?> constructor = clz.getConstructor(null);
        T item = (T) constructor.newInstance();
        Field[] declaredFields = clz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            field.set(item, resultSet.getObject(field.getName()));
        }
        return item;
    }

    public static <T> List<T> executeQuery(String sqlQuery) throws ClassNotFoundException {
        openConnectionToDB();
        List<T> results = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                results.add((T) createObject());
            }
            closeConnectionToDB();
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return results;
    }


    protected static void closeConnectionToDB() {
        try {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (con != null)
                con.close();
        } catch (Exception e) {
            logger.error("failed to close the connection");
        }
    }
}



package repository;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Repository<T> {
    static Class<?> clz;
    static Connection con=null;
    static Statement statement=null;
    static ResultSet resultSet=null;
    PreparedStatement preparedStatement = null;

    public Repository(Class <T> clz) {
        this.clz=clz;
    }


    public static void openConnectionToDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost:3307/mydb",  "root", "mydbuser");
        statement=con.createStatement();
    }
    public static <T> T createObject() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        Constructor<?> constructor= clz.getConstructor(null);
        T item= (T) constructor.newInstance();
        Field[] declaredFields= clz.getDeclaredFields();
        for (Field field: declaredFields) {
            field.setAccessible(true);
            field.set(item, resultSet.getObject(field.getName()));
        }
        return item;
    }

    public static <T> List<T> executeQuery(String sqlQuery) throws SQLException, ClassNotFoundException {
        openConnectionToDB();
        List<T> results= new ArrayList<>();
        try {
            resultSet=  statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                results.add((T) createObject());
            }
            close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
        return results;
    }


    private static void close() {
        try {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (con != null)
                con.close();
        } catch (Exception e) {
        }
    }


}



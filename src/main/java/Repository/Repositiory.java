package Repository;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Repositiory<T> {
    private Class<T> clz;
    public Repositiory(Class <T> clz) {
        this.clz=clz;
    }

    public List <T> executeQuery()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb",
                    "root", "mydbuser");

            Statement statement= con.createStatement();
            ResultSet resultSet= statement.executeQuery(String.format("select * from %s", clz.getSimpleName().toLowerCase()));

            List<T> results= new ArrayList<>();
            while (resultSet.next()) {
                Constructor<T> constructor= clz.getConstructor(null);
                T item= constructor.newInstance();
                Field[] declaredFields= clz.getDeclaredFields();
                for (Field field: declaredFields) {
                    field.setAccessible(true);
                    field.set(item, resultSet.getObject(field.getName()));
                }
                results.add(item);
            }
            con.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    }
}



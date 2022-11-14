package Repository;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Repository<T> {
    Class<T> clz;
    Connection con=null;
    Statement statement=null;
    ResultSet resultSet=null;
    PreparedStatement preparedStatement = null;

    public Repository(Class <T> clz) {
        this.clz=clz;
    }


    public void openConnectionToDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",  "root", "mydbuser");
        statement=con.createStatement();
    }

    T createObject() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        Constructor<T> constructor= clz.getConstructor(null);
        T item= constructor.newInstance();
        Field[] declaredFields= clz.getDeclaredFields();
        for (Field field: declaredFields) {
            field.setAccessible(true);
            field.set(item, resultSet.getObject(field.getName()));
        }
        return item;
    }

    public List <T> executeQuery(String sqlQuery) throws SQLException, ClassNotFoundException {
        openConnectionToDB();
        List<T> results= new ArrayList<>();
        try {
            resultSet=  statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                results.add(createObject());
            }
            close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
        return results;
    }


    public List<T> findAll () throws SQLException, ClassNotFoundException {
        return executeQuery(String.format("select * from %s", this.clz.getSimpleName().toLowerCase()));
    }

    private void close() {
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

    public T findOnebyId (int id) throws SQLException, ClassNotFoundException {
        try {
            openConnectionToDB();
            preparedStatement= con.prepareStatement(String.format("select * from %s where id= %d", this.clz.getSimpleName().toLowerCase(),id));
            ResultSet myRes= preparedStatement.executeQuery(String.format("select * from %s where id= %d", this.clz.getSimpleName().toLowerCase(),id));
            while (resultSet.next()) {
                 return createObject();
            }
            con.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
        return null;
    }


    public <T,K> List<T> findOneByProperty (K property) throws SQLException, ClassNotFoundException {
        try {
            openConnectionToDB();
            preparedStatement= con.prepareStatement(String.format("select * from {0} where property= {1}",this.clz.getSimpleName().toLowerCase(),property));
            ResultSet myRes= preparedStatement.executeQuery();
            List<T> results= new ArrayList<>();
            while (resultSet.next()) {
                results.add((T) createObject());
            }
            con.close();
            return results;
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
        return null;
    }




}


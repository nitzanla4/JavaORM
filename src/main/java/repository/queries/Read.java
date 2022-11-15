package repository.queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Read {

    private static Logger logger = LogManager.getLogger(Read.class.getName());

    private static Statement statement = null;

    public static <T> List<T> readAll(Class<?> clz) {
        try(Connection con = ConnectionToDB.openConnectionToDB()) {
            statement = con.createStatement();
            String query = "select * from " + clz.getSimpleName().toLowerCase();
            return executeReadQuery(statement.executeQuery(query), clz);
        } catch (SQLException e) {
            logger.error("cant execute Update the statement SQL Exception");
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T readOneById(Class<?> clz, int id)  {
        try(Connection con = ConnectionToDB.openConnectionToDB()) {
            String query = String.format("select * from %s where id= %d", clz.getSimpleName().toLowerCase(), id);
            statement = con.createStatement();
            return (T) executeReadQuery(statement.executeQuery(query), clz).stream().findFirst();
        } catch (SQLException e) {
            logger.error("cant execute Update the statement SQL Exception");
            e.printStackTrace();
        }
        return null;
    }


    public static <T,K> List<T> readByProperty (Class<?> clz, K property , String colName) {
        String query= "select * from " +clz.getSimpleName().toLowerCase() + " where "+ colName+ " = ";
        if (property.getClass()== String.class)
            query += "'"+ property+"'";
        else query+= property;

        try(Connection con = ConnectionToDB.openConnectionToDB()) {
            statement = con.createStatement();
            return executeReadQuery(statement.executeQuery(query), clz);
        } catch (SQLException e) {
            logger.error("cant execute Update the statement SQL Exception");
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T createObject(ResultSet resultSet, Class<?> clz) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Constructor<?> constructor = clz.getConstructor(null);
        T item = (T) constructor.newInstance();
        Field[] declaredFields = clz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                field.set(item, resultSet.getObject(field.getName()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return item;
    }

    public static <T> List<T> executeReadQuery(ResultSet resultSet, Class<?> clz) {
        List<T> results = new ArrayList<>();
        try {
            while (resultSet.next()) {
                results.add(createObject(resultSet, clz));
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return results;
    }
}

package repository.queries;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Add {
    private static Logger logger = LogManager.getLogger(Add.class.getName());
    private static Statement statement = null;

    public static <T> void addSingleItem(Class<?> clz, T item) {
        try(Connection con = ConnectionToDB.openConnectionToDB()) {
            statement = con.createStatement();
            add(clz, item);
        } catch (SQLException | IllegalAccessException e) {
            logger.error("cant execute Update the statement SQL Exception");
            e.printStackTrace();
        }
    }

    public static <T> void addMultipleItem(Class<?> clz, List<T> items) {
        try(Connection con = ConnectionToDB.openConnectionToDB()) {
            statement = con.createStatement();
            for (T item : items) {
                add(clz, item);
            }
        } catch (SQLException | IllegalAccessException e) {
            logger.error("cant execute Update the statement SQL Exception");
            e.printStackTrace();
        }
    }

    public static <T> void add(Class<?> clz, T item) throws IllegalAccessException {
        String SQL_Statement= createAddSQLStatement(clz, item);
        try {
            statement.executeUpdate(SQL_Statement);
        } catch (SQLException e) {
            logger.error("cant executeUpdate the statement SQL Exception");
            e.printStackTrace();
        }
    }

    private static <T> String createAddSQLStatement(Class<?> clz, T item) throws IllegalAccessException {
        String SQL_Statement = String.format("INSERT INTO %s ", clz.getSimpleName().toLowerCase() + "(");
        Field[] declaredFields = clz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            System.out.println(field + ", " + item);
            SQL_Statement += field.getName() + ",";
        }
        SQL_Statement=SQL_Statement.substring(0, SQL_Statement.length() - 1); //remove last ,
        SQL_Statement += ")";
        SQL_Statement += "VALUES(";

        for (Field field : declaredFields) {
            field.setAccessible(true);
            System.out.println(field + ", " + item);
            SQL_Statement += "'"+ field.get(item) + "' ,";
        }
        SQL_Statement=SQL_Statement.substring(0, SQL_Statement.length() - 1); //remove last ,
        SQL_Statement += ")";
        System.out.println(SQL_Statement);
        return SQL_Statement;
    }
}


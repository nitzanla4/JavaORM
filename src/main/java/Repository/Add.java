package Repository;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Add<T> extends RepositoryFacade<T> {

    public Add(Class<T> clz) {
        super(clz);
    }

    public void addSingleItem(T item) throws SQLException, ClassNotFoundException {
        openConnectionToDB();
        add(item);
        closeConnectionToDB();
    }
    public void addMultipleItem(List<T> items) throws SQLException, ClassNotFoundException {
        openConnectionToDB();
        for(T item:items){
            add(item);
        }
        closeConnectionToDB();
    }

    public void add(T item) throws SQLException, ClassNotFoundException {
        //example: statement.executeUpdate("INSERT INTO Customers " + "VALUES (1001, 'Simpson', 'Mr.', 'Springfield', 2001)");
        String SQL_Statement = String.format("INSERT INTO %s", this.clz.getSimpleName().toLowerCase());
        SQL_Statement += "VALUES(";

        Field[] declaredFields = clz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            SQL_Statement += field.getName() + ",";
        }
        SQL_Statement.substring(0, SQL_Statement.length() - 1); //remove last ,
        SQL_Statement += ")";
        statement.executeUpdate(SQL_Statement);
        closeConnectionToDB();
    }


}


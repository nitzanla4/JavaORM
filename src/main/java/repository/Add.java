package repository;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Add<T> extends Repository<T> {

    public Add(Class<T> clz) {
        super(clz);
    }

    //---add---
    public void addSingleItem(T item) throws SQLException, ClassNotFoundException, IllegalAccessException {
        openConnectionToDB();
        add(item);
        close();
    }


    public void addMultipleItem(List<T> items) throws SQLException, ClassNotFoundException, IllegalAccessException {
        openConnectionToDB();
        for(T item:items){
            add(item);
        }
        close();
    }

    public void add(T item) throws SQLException, IllegalAccessException {
        //example: statement.executeUpdate("INSERT INTO Customers " + "VALUES (1001, 'Simpson', 'Mr.', 'Springfield', 2001)");
        String SQL_Statement = String.format("INSERT INTO %s ", this.clz.getSimpleName().toLowerCase());
        SQL_Statement += "VALUES(";

        Field[] declaredFields = clz.getDeclaredFields();
        for (Field field : declaredFields) {
            SQL_Statement+="'";
            field.setAccessible(true);
            SQL_Statement += field.get(item) + "',";
        }
        SQL_Statement=SQL_Statement.substring(0, SQL_Statement.length() - 1); //remove last ,
        SQL_Statement += ")";
        System.out.println(SQL_Statement);
        statement.executeUpdate(SQL_Statement);
     //   close();
    }
}


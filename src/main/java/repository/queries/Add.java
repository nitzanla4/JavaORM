package repository.queries;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entities.Primary;
import org.example.entities.Unique;

public class Add<T> extends Repository<T> {
    private static Logger logger = LogManager.getLogger(Add.class.getName());
    public Add(Class<T> clz) {
        super(clz);
    }

    public void addSingleItem(T item) throws ClassNotFoundException, IllegalAccessException {
        openConnectionToDB();
        add(item);
        closeConnectionToDB();
    }

    public void addMultipleItem(List<T> items) throws ClassNotFoundException, IllegalAccessException {
        openConnectionToDB();
        for(T item:items){
            add(item);
        }
        closeConnectionToDB();
    }

    public void add(T item) throws IllegalAccessException {
        String SQL_Statement= createAddSQLStatement(item);
        try {
            statement.executeUpdate(SQL_Statement);
        } catch (SQLException e) {
            logger.error("cant executeUpdate the statement SQL Exception");
            e.printStackTrace();
        }
    }

    private String createAddSQLStatement(T item) throws IllegalAccessException {
        String SQL_Statement = String.format("INSERT INTO %s ", this.clz.getSimpleName().toLowerCase());
        SQL_Statement += "VALUES(";
        Field[] declaredFields = clz.getDeclaredFields();
        for (Field field : declaredFields) {
            SQL_Statement+="'";
            field.setAccessible(true);
            SQL_Statement += field.get(item);
            SQL_Statement +="',";
        }

        SQL_Statement=SQL_Statement.substring(0, SQL_Statement.length() - 1); //remove last ,
        SQL_Statement += ")";
        return SQL_Statement;
    }


}


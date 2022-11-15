package repository.queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entities.Column;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateTable<T> extends Repository<T> {
    public CreateTable(Class<T> clz) {
        super(clz);
    }
    private static Logger logger = LogManager.getLogger(CreateTable.class.getName());

    public void createNewTable(T entity) throws ClassNotFoundException {
        try {
            openConnectionToDB();
            String query= getStringQuery(entity);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("SQL Exception");
            e.printStackTrace();
        }
        closeConnectionToDB();
    }

    private String getStringQuery(T entity)  {
        String tableName= entity.getClass().getSimpleName().toLowerCase();
        List<Column> columns= new ArrayList<>();

        Field[] declaredFields = clz.getDeclaredFields();
        String type;
        for (Field field : declaredFields) {
            field.setAccessible(true);
            type=typeValidation(field);
            columns.add(new Column(type,field.getName() ));
        }

        String query = createQueryString(tableName,columns);
        return query;
    }

    private String createQueryString(String tableName, List<Column> columns) {

        String query = "create table " + tableName+ " ( ";
        for(Column col: columns){
            query += col.getName() + " " + col.getType() +" , ";
        }
        query=query.substring(0, query.length()-2); //remove last ,
        query+= " ) ";
        return query;
    }

    private String typeValidation(Field field){
        String type=field.getType().getSimpleName();

        if(field.getType().isAssignableFrom(String.class)){
            logger.info("String type cast to longtext");
            type="longtext";
        }
        return type;
    }



}

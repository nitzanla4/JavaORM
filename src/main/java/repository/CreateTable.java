package repository;

import org.example.Column;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class CreateTable<T> extends Repository<T> {
    public CreateTable(Class<T> clz) {
        super(clz);
    }
// Create a table based on an entity

    public void createNewTable(T entity) throws IllegalAccessException, SQLException, ClassNotFoundException {
        openConnectionToDB();
        String query= getStringQuery(entity);
        statement.executeUpdate(query);
        close();
    }

    private String getStringQuery(T entity) throws IllegalAccessException {
        String tableName= entity.getClass().getSimpleName().toLowerCase();
        List<Column> column= new ArrayList<>();
        //HashMap<String, String> column= new List<String, String>();

        Field[] declaredFields = clz.getDeclaredFields();
        String type;
        for (Field field : declaredFields) {
            field.setAccessible(true);
            type=field.getType().getSimpleName();

            if(field.getType().isAssignableFrom(String.class)){
                type="longtext";
            }

            System.out.println("Variable type: "+type+" ,Variable name: "+field.getName()+" ,Value: "+ field.get(entity).toString());
            column.add(new Column(type,field.getName() ));
        }


        System.out.println("columns: "+column);

        String query = "create table " + tableName+ " ( ";
        for(Column col: column){
            query += col.getName() + " " + col.getType() +" , ";
        }

        query=query.substring(0, query.length()-2); //remove last ,
        query+= " ) ";
        System.out.println("query : " + query);
        return query;
    }

}

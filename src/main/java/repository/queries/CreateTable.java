package repository.queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entities.Column;
import org.example.entities.Primary;
import org.example.entities.Unique;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateTable {
    private static Logger logger = LogManager.getLogger(CreateTable.class.getName());

    private static Statement statement = null;

    public static <T> void createNewTable(Class<?> clz, T entity) {
        try(Connection con = ConnectionToDB.openConnectionToDB()) {
            statement = con.createStatement();
            String query = getStringQuery(clz, entity);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("cant execute Update the statement SQL Exception");
            e.printStackTrace();
        }
    }

    private static <T> String getStringQuery(Class<?> clz, T entity)  {
        String tableName= entity.getClass().getSimpleName().toLowerCase();
        HashMap<String,String> columns=new HashMap<>();
        String query = "create table " + tableName+ " ( ";

        Field[] declaredFields = clz.getDeclaredFields();
        String type;
        String annotation;

        for (Field field : declaredFields) {
            field.setAccessible(true);
            type = typeValidation(field);
            query += type + " " + field.getName();

            annotation = addAnnotation(field);
            if(annotation!=null){
                query +=annotation;
            }
            query +=" , ";
        }
        query=query.substring(0, query.length()-2); //remove last ,
        query+= " ) ";
        return query;
    }

    private static String createQueryString(String tableName, List<Column> columns) {
        String query = "create table " + tableName+ " ( ";
        for(Column col: columns){
            query += col.getName() + " " + col.getType() +" , ";
        }
        query=query.substring(0, query.length()-2); //remove last ,
        query+= " ) ";
        return query;
    }

    private static String typeValidation(Field field){
        String type=field.getType().getSimpleName();

        if(field.getType().isAssignableFrom(String.class)){
            logger.info("String type cast to longtext");
            type="longtext";
        }
        if(!field.getType().isPrimitive()){
            logger.info("field is not primitive type");
            type="json";
        }
        if(field.getType().isInstance(int.class)){
            logger.info("field is not primitive type");
            type="INTEGER";
        }

        return type;
    }
    private static String addAnnotation(Field field){
        Annotation[] annotations= field.getAnnotations();
        for(Annotation annotation:annotations){
            if(annotation instanceof Primary){
                return " NOT NULL PRIMARY KEY AUTO_INCREMENT";
            }
            else{
                if(annotation instanceof Unique){
                    return " NOT NULL UNIQUE";
                }
            }
        }
        return null;
    }
}

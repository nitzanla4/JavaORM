package repository;

import java.lang.reflect.Field;
import java.util.HashMap;

public class CreateTable<T> extends Repository<T> {
    public CreateTable(Class<T> clz) {
        super(clz);
    }
// Create a table based on an entity

    public void createNewTable(T entity) throws IllegalAccessException {
        String tableName= entity.getClass().getSimpleName();
        HashMap<String, String> column= new HashMap<>();
        Field[] declaredFields = clz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            column.put(field.getName(), field.get(entity).toString());
            System.out.println("Variable type: "+field.getType().getSimpleName()+" ,Variable name: "+field.getName()+" ,Value: "+ field.get(entity).toString());
        }
    }

}

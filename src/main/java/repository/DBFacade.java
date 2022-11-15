package repository;

import org.example.entities.User;
import repository.queries.*;

import java.util.List;

public class DBFacade<T> {
    private Class<?> clz;

    public DBFacade(Class<T> clz) {
        this.clz = clz;
    }

    public Class<?> getClz() {
        return clz;
    }

    public <T> void addSingleItem(Class<?> clz, T item ) {
        Add.addSingleItem(clz, item);
    }

    public void addMultipleItem(Class<?> clz,List<T> items) {
        Add.addMultipleItem(clz, items);
    }

    public T readOneById(int id) {
        return Read.readOneById(clz, id);
    }

    public <T,K> List<T> readByProperty (K property , String colName)
    {
        return Read.readByProperty(clz, property, colName);
    }

    public <T,K> void deleteOneByProperty (K property , String colName) {
        Delete.deleteOneByProperty(clz, property,colName);
    }

    public <T,K> void updateOneByProperty (String colName, T property, String filterBy, K filterValue)
    {
        Update.updateOneByProperty(clz, colName, property, filterBy, filterValue);
    }

    public <T> void createNewTable(T newTable) {
        CreateTable.createNewTable(clz, newTable);
    }

    public <T> void deleteEntireTable(String tableName) {
        Delete.deleteEntireTable(tableName);
    }
}


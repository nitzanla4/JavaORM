package repository;

import repository.queries.*;

import java.sql.SQLException;
import java.util.List;

public class DBFacade<T> {
    Class<T> clz;

    private Read read;
    private Add add;
    private Update update;
    private Delete delete;
    private CreateTable createTable;

    public DBFacade(Class<T> clz) {
        this.clz = clz;
        this.read = new Read<>(clz);
        this.add = new Add<>(clz);
        this.update = new Update<>(clz);
        this.delete = new Delete<>(clz);
        this.createTable = new CreateTable<>(clz);
    }


    public <T> void addSingleItem(T item) throws ClassNotFoundException, IllegalAccessException {
        add.addSingleItem(item);
    }

    public void addMultipleItem(List<T> items) throws ClassNotFoundException, IllegalAccessException {
    add.addMultipleItem(items);
    }


    public T findOneById(int id) throws ClassNotFoundException {
       return (T) read.findOneById(id);
    }

    public <T,K> List<T> findByProperty (K property , String colName) throws ClassNotFoundException
    {
        return read.findByProperty(property,colName);
    }

    public <T,K> void deleteOneByProperty (K property , String colName) throws SQLException, ClassNotFoundException {
        delete.deleteOneByProperty(property,colName);
    }

}


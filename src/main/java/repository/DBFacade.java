package repository;

import repository.queries.*;

public class DBFacade<T> {
    Class<T> clz;
    public DBFacade(Class <T> clz) {
        this.clz=clz;
    }
    public Read read=new Read(clz);
    public Add add=new Add(clz);
    public Update update=new Update( clz);
    public Delete delete=new Delete( clz);
    public CreateTable createTable=new CreateTable<>(clz);


}


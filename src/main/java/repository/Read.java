package repository;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Read<T> extends Repository<T> {

    public Read(Class<T> clz) {
        super(clz);
    }

    public List<T> findAll () throws SQLException, ClassNotFoundException {
        return executeQuery(String.format("select * from %s", this.clz.getSimpleName().toLowerCase()));
    }

    public T findOneById(int id) throws SQLException, ClassNotFoundException {
        List<T> res= (List<T>) executeQuery(String.format("select * from %s where id= %d", this.clz.getSimpleName().toLowerCase(), id));
        return (T) res.stream().findFirst();
    }

    public <T,K> List<T> findByProperty (K property) throws SQLException, ClassNotFoundException {

            String str1 ="select * from "+this.clz.getSimpleName().toLowerCase() + " where id = " +property;
            List<T> res= executeQuery(str1);
            if (res!= null) return res;
            else return null;
    }




}

package repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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


    public <T,K> List<T> findByProperty (K property , String colName) throws SQLException, ClassNotFoundException {
        String str= "select * from " +this.clz.getSimpleName().toLowerCase() + " where "+ colName+ " = ";

        if (property.getClass()== String.class)
            str += "'"+ property+"'";
        else str+= property;

        System.out.println(str);
        return executeQuery(str);

    }


}

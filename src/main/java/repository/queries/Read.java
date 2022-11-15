package repository.queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class Read<T> extends Repository<T> {

    public Read(Class<T> clz) {
        super(clz);
    }
    private static Logger logger = LogManager.getLogger(Read.class.getName());


    public List<T> findAll () throws SQLException, ClassNotFoundException {
        return executeQuery(String.format("select * from %s", this.clz.getSimpleName().toLowerCase()));
    }

    public T findOneById(int id) throws ClassNotFoundException {
        List<T> res= null;
        res = (List<T>) executeQuery(String.format("select * from %s where id= %d", this.clz.getSimpleName().toLowerCase(), id));
        return (T) res.stream().findFirst();
    }


    public <T,K> List<T> findByProperty (K property , String colName) throws ClassNotFoundException {
        String str= "select * from " +this.clz.getSimpleName().toLowerCase() + " where "+ colName+ " = ";

        if (property.getClass()== String.class)
            str += "'"+ property+"'";
        else str+= property;

        //System.out.println(str);
        return executeQuery(str);
    }


}

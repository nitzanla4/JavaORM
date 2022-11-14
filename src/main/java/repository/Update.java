package repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;
import java.util.List;

public class Update<T> extends Repository<T>{
    public Update(Class<T> clz) {
        super(clz);
    }
    private static Logger logger = LogManager.getLogger(Update.class.getName());


    public <T,K> void updateOneByProperty (K property , String colName, int id) throws ClassNotFoundException {
        openConnectionToDB();
        updateByProperty(property,colName, id);
        closeConnectionToDB();
    }

    public <T,K> List<T> updateByProperty (K property , String colName, int id) throws ClassNotFoundException {
        String str= "UPDATE " + this.clz.getSimpleName().toLowerCase() +
                " SET " + colName + "=";
        if(property.getClass() == String.class)  {
            str += "'" + property + "'";
        }
        else str += property;
        str += " WHERE " + "id=" + id;

        System.out.println(str);
        return executeQuery(str);
    }
}

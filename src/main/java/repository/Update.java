package repository;

import java.sql.SQLException;
import java.util.List;

public class Update<T> extends Repository<T>{
    public Update(Class<T> clz) {
        super(clz);
    }

    public <T,K> void updateOneByProperty (K property , String colName, int id) throws SQLException, ClassNotFoundException {
        openConnectionToDB();
        updateByProperty(property,colName, id);
        close();
    }

    public <T,K> List<T> updateByProperty (K property , String colName, int id) throws SQLException, ClassNotFoundException {
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

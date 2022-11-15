package repository.queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Update<T> extends Repository<T>{
    public Update(Class<T> clz) {
        super(clz);
    }
    private static Logger logger = LogManager.getLogger(Update.class.getName());


    public <T,K> void updateOneByProperty (String colName, T property, String filterBy, K filterValue)  {
        try {
            openConnectionToDB();
            updateByProperty(colName, property, filterBy, filterValue);
            closeConnectionToDB();
        } catch (ClassNotFoundException e) {
            logger.error("class not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T,K> void updateByProperty (String colName, T property, String filterBy, K filterValue) throws ClassNotFoundException, SQLException {
        String str= "UPDATE " + this.clz.getSimpleName().toLowerCase() +
                " SET " + colName + "=";
        if(property.getClass() == String.class)  {
            str += "'" + property + "'";
        }
        else str += property;
        str += " WHERE " + filterBy + "=";
        if(filterValue.getClass() == String.class)  {
            str += "'" + filterValue + "'";
        }
        else str += filterValue;

        System.out.println(str);
        statement= (Statement) con.createStatement();
        int rows = statement.executeUpdate(str);
        System.out.println("Rows impacted : " + rows );
    }
}

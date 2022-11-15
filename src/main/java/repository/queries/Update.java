package repository.queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {

    private static Logger logger = LogManager.getLogger(Update.class.getName());

    private static Statement statement = null;

    public static <T,K> void updateOneByProperty (Class<?> clz, String colName, T property, String filterBy, K filterValue)  {
        try(Connection con = ConnectionToDB.openConnectionToDB()) {
            statement = con.createStatement();
            statement.executeUpdate(updateByProperty(clz, colName, property, filterBy, filterValue));
        } catch (SQLException e) {
            logger.error("cant execute Update the statement SQL Exception");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T,K> String updateByProperty (Class<?> clz, String colName, T property, String filterBy, K filterValue) throws ClassNotFoundException, SQLException {
        String str= "UPDATE " + clz.getSimpleName().toLowerCase() +
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
        return str;
    }
}

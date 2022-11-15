package repository.queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    private static Logger logger = LogManager.getLogger(Delete.class.getName());

    private static Statement statement = null;


    //DELETE FROM table_name WHERE condition;
    public static <K> void deleteOneByProperty (Class<?> clz, K property , String colName) {
        //Unique
        try(Connection con = ConnectionToDB.openConnectionToDB()) {
            statement = con.createStatement();
            String query = delete(clz, property, colName);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("cant execute Update the statement SQL Exception");
            e.printStackTrace();
        }
    }

    public static  <K> void deleteMultipleItem (Class<?> clz, K property , String colName) {
        try(Connection con = ConnectionToDB.openConnectionToDB()) {
            statement = con.createStatement();
            String query = delete(clz, property, colName);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("cant execute Update the statement SQL Exception");
            e.printStackTrace();
        }
    }

    public static void deleteEntireTable(String tableName)  {
        try(Connection con = ConnectionToDB.openConnectionToDB()) {
            statement = con.createStatement();
            String query = "drop table " + tableName;
            statement.executeUpdate(query);
            logger.info("Table is deleted from the table successfully..................");
        } catch (SQLException e) {
            logger.error("cant execute Update the statement SQL Exception");
            e.printStackTrace();
        }
    }

    public static <K> String delete (Class<?> clz, K property , String colName) {
        String str= "delete from " + clz.getSimpleName().toLowerCase() + " where "+ colName+ " = ";

        if (property.getClass()== String.class) str += "'"+ property+"'";
        else str+= property;

        return str;
    }
}
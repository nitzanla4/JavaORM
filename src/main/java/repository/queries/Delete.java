package repository.queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

public class Delete<T> extends Repository<T>{
    public Delete(Class<T> clz) {
        super(clz);
    }
    private static Logger logger = LogManager.getLogger(Delete.class.getName());


    //DELETE FROM table_name WHERE condition;
    public <T,K> void deleteOneByProperty (K property , String colName) throws SQLException, ClassNotFoundException {
        openConnectionToDB();
        delete(property,colName);
        closeConnectionToDB();
    }

    public <K> void delete (K property , String colName) {
            String str= "delete from " +this.clz.getSimpleName().toLowerCase() + " where "+ colName+ " = ";

            if (property.getClass()== String.class) str += "'"+ property+"'";
            else str+= property;

            System.out.println(str);

        try {
            statement= (Statement) con.createStatement();
            statement.executeUpdate(str);
            logger.info("Record is deleted from the table successfully..................");
        } catch (SQLException e) {
            logger.error("SQL Exception");
            e.printStackTrace();
        }

        }

    public <K> void deleteMultipleItem (K property , String colName) throws SQLException, ClassNotFoundException, IllegalAccessException {
        openConnectionToDB();
        delete(property,colName);
        closeConnectionToDB();
    }

    public void deleteEntireTable(String tableName)  {
        String str= "drop table "+tableName;
        try {
            statement= (Statement) con.createStatement();
            statement.executeUpdate(str);
            logger.info("Table is deleted from the table successfully..................");
        } catch (SQLException e) {
            logger.error("SQL Exception");
            e.printStackTrace();
        }
    }

}




//    Single item deletion by any property (delete user with email x)
//    Multiple item deletion by any property (delete all users called x)
//    Delete entire table (truncate)
package repository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Delete<T> extends Repository<T>{
    public Delete(Class<T> clz) {
        super(clz);
    }

    //DELETE FROM table_name WHERE condition;
    public <T,K> void deleteOneByProperty (K property , String colName) throws SQLException, ClassNotFoundException {
        openConnectionToDB();
        delete(property,colName);
        close();
    }

    public <K> void delete (K property , String colName) throws SQLException {
            String str= "delete from " +this.clz.getSimpleName().toLowerCase() + " where "+ colName+ " = ";

            if (property.getClass()== String.class) str += "'"+ property+"'";
            else str+= property;

            System.out.println(str);

            statement= (Statement) con.createStatement();
            statement.executeUpdate(str);
            System.out.println("Record is deleted from the table successfully..................");

        }

//    public <K> void deleteMultipleItem (K property , String colName) throws SQLException, ClassNotFoundException, IllegalAccessException {
//        openConnectionToDB();
//
//        close();
//
//    }

    public void deleteEntireTable() throws SQLException {
        String str= "drop table " +this.clz.getSimpleName().toLowerCase();

        statement= (Statement) con.createStatement();
        statement.executeUpdate(str);
        System.out.println("Table is deleted from the table successfully..................");
    }

}




//    Single item deletion by any property (delete user with email x)
//    Multiple item deletion by any property (delete all users called x)
//    Delete entire table (truncate)
//package Repository;
//
//import repository.Repository;
//
//import java.lang.reflect.Field;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.List;
//
//public class Add<T> extends Repository<T> {
//
//    public Add(Class<T> clz) {
//        super(clz);
//    }
//    //---add---
////    public void addSingleItem(T item) throws SQLException, ClassNotFoundException {
////        openConnectionToDB();
////        add(item);
////        closeConnectionToDB();
//    }
//    public void addMultipleItem(List<T> items) throws SQLException, ClassNotFoundException {
//        openConnectionToDB();
//        for(T item:items){
//            add(item);
//        }
//        closeConnectionToDB();
//    }
//
//    public void add(T item) throws SQLException {
//        //example: statement.executeUpdate("INSERT INTO Customers " + "VALUES (1001, 'Simpson', 'Mr.', 'Springfield', 2001)");
//        String SQL_Statement = String.format("INSERT INTO %s", this.clz.getSimpleName().toLowerCase());
//        SQL_Statement += "VALUES(";
//
//        Field[] declaredFields = clz.getDeclaredFields();
//        for (Field field : declaredFields) {
//            field.setAccessible(true);
//            SQL_Statement += field.getName() + ",";
//        }
//        SQL_Statement=SQL_Statement.substring(0, SQL_Statement.length() - 1); //remove last ,
//        SQL_Statement += ")";
//        statement.executeUpdate(SQL_Statement);
//        closeConnectionToDB();
//    }
//
//    //---update---
//
//    public void update(T item ) throws SQLException {
//        //TODO- implement again
//        String sql =
//                "UPDATE GLOBALSETTINGS " +
//                        "  SET settingValue = ? " +
//                        "WHERE settingName = ?";
//        PreparedStatement pstmt = con.prepareStatement(sql);
//
//        Field[] declaredFields = clz.getDeclaredFields();
//
//        int i=0;
//        for (Field field : declaredFields) {
//            pstmt.setString(++i, field.getName());
//        }
//        pstmt.executeUpdate();
//    }
//
//
//
//
//
//
//}
//

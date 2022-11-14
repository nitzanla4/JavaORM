//package Repository;
//
//import java.sql.*;
//
//public class RepositoryFacade<T> {
//    Class<T> clz;
//    Connection con=null;
//    Statement statement=null;
//    ResultSet resultSet=null;
//    PreparedStatement preparedStatement = null;
//
//    private Repository<T> repository;  //for connectionDB and close functions
//
//
//    public RepositoryFacade(Class <T> clz) {
//        this.clz=clz;
//    }
//
//    private Add add=new Add(clz);
//    private Read read=new Read( clz);
//    private Delete delete=new Delete( clz);
//
//
//
//}
//

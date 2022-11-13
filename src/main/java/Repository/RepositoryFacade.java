package Repository;

import java.sql.*;

public class RepositoryFacade<T> {
    Class<T> clz;
    Connection con=null;
    Statement statement=null;
    ResultSet resultSet=null;
    PreparedStatement preparedStatement = null;

    public RepositoryFacade(Class <T> clz) {
        this.clz=clz;
    }

    private Add add=new Add(clz);
    private Read read=new Read( clz);
    private Delete delete=new Delete( clz);



    void openConnectionToDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "mydbuser");
        statement = con.createStatement();
    }
    void closeConnectionToDB() {
        try {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (con != null)
                con.close();
        } catch (Exception e) {
        }
    }
}


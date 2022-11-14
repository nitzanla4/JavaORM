package Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Read<T> extends Repository<T> {
    public Read(Class<T> clz) {
        super(clz);
    }


    public T findOneById (int id) throws SQLException, ClassNotFoundException {
        try {
            openConnectionToDB();
            preparedStatement= con.prepareStatement(String.format("select * from %s where id= %d", this.clz.getSimpleName().toLowerCase(),id));
            ResultSet myRes= preparedStatement.executeQuery();
            while (resultSet.next()) {
                return createObject();
            }
            con.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
        return null;
    }

    public <T,K> List<T> findOneByProperty (K property) throws SQLException, ClassNotFoundException {
        try {
            openConnectionToDB();
            preparedStatement= con.prepareStatement(String.format("select * from {0} where property= {1}",this.clz.getSimpleName().toLowerCase(),property));
            ResultSet myRes= preparedStatement.executeQuery();
            List<T> results= new ArrayList<>();
            while (resultSet.next()) {
                results.add((T) createObject());
            }
            con.close();
            return results;
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
        return null;
    }

}

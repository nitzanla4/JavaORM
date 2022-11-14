package repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Read<T> extends Repository<T> {

    public Read(Class<T> clz) {
        super(clz);
    }


    public List<T> findAll () throws SQLException, ClassNotFoundException {
        return executeQuery(String.format("select * from %s", this.clz.getSimpleName().toLowerCase()));
    }

    public T findOneById(int id) throws SQLException, ClassNotFoundException {
        List<T> res= (List<T>) executeQuery(String.format("select * from %s where id= %d", this.clz.getSimpleName().toLowerCase(), id));
        return (T) res.stream().findFirst();
    }
    public <T,K> Optional<T> findOneByProperty (K property) throws SQLException, ClassNotFoundException {
        List<T> res= (List<T>) executeQuery(String.format("select * from {0} where property= {1}",this.clz.getSimpleName().toLowerCase(),property));
        return res.stream().findFirst();
    }


    /*public <T,K> List<T> findOneByProperty (K property) throws SQLException, ClassNotFoundException {
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
    }*/


}

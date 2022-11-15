
import org.example.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.DBFacade;

public class AuthenticationTest {
    static DBFacade<User> facadeUser;

    @BeforeAll
    public static void beforeAll() {
        facadeUser = new DBFacade<>(User.class);
    }

    @Test
    public void addNullItem() {
        User user= new User();
        Assertions.assertThrows(NullPointerException.class, () -> facadeUser.addSingleItem(user.getClass(),null));
    }

    @Test
    public void deletedItem_Null_Exception() {
        Assertions.assertThrows(NullPointerException.class, () -> facadeUser.deleteOneByProperty(null, "id"));
    }

    //String colName, T property, String filterBy, K filterValue)
    // name  sivan    by email
    @Test
    public void updateItem_correctName_Updated() {
        Assertions.assertThrows(NullPointerException.class, () -> facadeUser.updateOneByProperty("name", "sivan", "email", "levi@gmail.com"));
    }
    


}

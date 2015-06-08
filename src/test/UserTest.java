import com.udl.softarch.randomfilms.models.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Adrian on 6/6/15.
 */
public class UserTest {

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @Test
    public void testUsername() throws Exception {
        user.setUsername("usertest");
        assertEquals("usertest", user.getUsername());
    }

    @Test
    public void testPassword() throws Exception {
        user.setPassword("pass");
        assertEquals("pass", user.getPassword());
    }

}

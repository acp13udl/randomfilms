import com.udl.softarch.randomfilms.config.SecurityConfig;
import com.udl.softarch.randomfilms.repositories.FilmRepository;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by Adrian on 6/6/15.
 */
@WebAppConfiguration
@ContextConfiguration(classes = SecurityConfig.class)
public class FilmsDefinitions {

    @Autowired
    WebApplicationContext webApp;

    private MockMvc mockMvc;

    @Before
    public void setup() {
    }

    @Test
    public void test_prueba(){

    }

    @Test
    public void test_prueba2(){

    }

    @After
    public void tearDown() throws Exception {
    }
}

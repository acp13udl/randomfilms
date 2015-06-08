import com.udl.softarch.randomfilms.config.RandomFilmsAppContext;
import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.models.User;
import com.udl.softarch.randomfilms.repositories.FilmRepository;
import com.udl.softarch.randomfilms.repositories.UserRepository;
import com.udl.softarch.randomfilms.services.FilmsPersonInvolvedService;
import com.udl.softarch.randomfilms.services.UserFilmsService;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by Adrian on 6/6/15.
 */
@WebAppConfiguration
@ContextConfiguration(classes = RandomFilmsAppContext.class)
public class UserFilmsDefinitions {

    @Autowired
    WebApplicationContext webApp;

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    FilmsPersonInvolvedService filmService;

    @Autowired
    UserFilmsService userFilmsService;

    @Autowired
    UserRepository userRepository;

    private MockMvc mockMvc;
    private ResultActions result;

    @Before
    public void setup() {

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApp)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Given("^films from films repository:$")
    public void get_all_films_from_film_repository(DataTable films) throws Throwable {

        Long index = 1L;
        for (Film f:films.asList(Film.class)){
            if (!filmRepository.exists(index))
                filmRepository.save(f);
            else if (!filmRepository.findOne(index).getTitle().equals(f.getTitle())) {
                Film beUpdate = filmService.getFilmAndPersonInvolved(index);
                Film updated = updateFilm(beUpdate,f);
                filmRepository.save(updated);
            }
            index++;
        }
    }

    private Film updateFilm(Film one,Film two){ //if title is equals all static content is the same.

        one.setActors(two.getActors());
        one.setDirectors(two.getDirectors());
        one.setReviews(two.getReviews());

        return two;
    }


    @And("^one greeting has id (\\d+) and content \"([^\"]*)\"$")
    public void one_greeting_has_id_and_content(int id, String title) throws Throwable {
        result.andExpect(jsonPath("$[?(@.id =='"+id+"')].title").value(hasItem(title)));
    }

    @When("^the client requests greeting with id (\\d+)$")
    public void the_client_requests_greeting_with_id(int id) throws Throwable {
        result = mockMvc.perform(get("/films/{id}", id)
                .accept(MediaType.APPLICATION_JSON));
    }

    @After
    public void tearDown() throws Exception {
    }
}

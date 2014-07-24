package net.cyphoria.cylus.acceptancetest;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.cyphoria.cylus.Cylus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriver;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
@WebAppConfiguration
@ContextConfiguration(classes = Cylus.class)
public class HelloWorldStep {

    @Autowired
    private WebApplicationContext context;

    MockMvcHtmlUnitDriver driver;

    @Before
    public void setup() throws IOException {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        driver = new MockMvcHtmlUnitDriver(mockMvc, true);
    }

    @After
    public void destroy() {
        if(driver != null) {
            driver.close();
        }
    }


    @Given("^die Anwendung ist gestartet$")
    public void die_Anwendung_ist_gestartet() throws Throwable {
    }

    @When("^ich die erste Seite aufrufe$")
    public void ich_die_erste_Seite_aufrufe() throws Throwable {
        driver.get("http://localhost/");
    }

    @Then("^kann ich \"([^\"]*)\" lesen$")
    public void kann_ich_lesen(String arg1) throws Throwable {
        assertThat(driver.getPageSource(), containsString(arg1));
    }
}

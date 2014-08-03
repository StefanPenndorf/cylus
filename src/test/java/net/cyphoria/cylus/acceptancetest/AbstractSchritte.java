/*
 * Copyright (c) Stefan Penndorf 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.cyphoria.cylus.acceptancetest;

import net.cyphoria.cylus.Cylus;
import org.fluentlenium.core.FluentAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriver;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
@WebAppConfiguration
@ContextConfiguration(classes = Cylus.class)
abstract class AbstractSchritte extends FluentAdapter {

    @Autowired
    private WebApplicationContext context;

    private MockMvcHtmlUnitDriver driver;

    @PostConstruct
    public void setup() throws IOException {
        final MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        driver = new MockMvcHtmlUnitDriver(mockMvc, true);
        // MockMvc will use cylus as servlet path but we don't use servlet
        withDefaultUrl("http://localhost/cylus/");
        initFluent(driver);
        initTest();
    }

    @PreDestroy
    public void destroy() {
        if(driver != null) {
            driver.close();
        }
    }

}

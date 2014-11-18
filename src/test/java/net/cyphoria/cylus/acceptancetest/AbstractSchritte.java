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
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(
                classes = Cylus.class,
                initializers = ConfigFileApplicationContextInitializer.class),
        @ContextConfiguration(classes = WebAppExecutingTestContext.class)
})
abstract class AbstractSchritte extends FluentAdapter {

    @Autowired
    private HtmlUnitDriver driver;

    @PostConstruct
    public void setup() throws IOException {
        withDefaultUrl("http://localhost/");
        initFluent(driver);
        initTest();
    }
}

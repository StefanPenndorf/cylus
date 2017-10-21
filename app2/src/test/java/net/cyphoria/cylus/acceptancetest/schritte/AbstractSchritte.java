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

package net.cyphoria.cylus.acceptancetest.schritte;

import net.cyphoria.cylus.Cylus2;
import net.cyphoria.cylus.acceptancetest.AcceptanceTestConfig;
import org.fluentlenium.adapter.FluentAdapter;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.PostConstruct;

/**
 * @author Stefan Pennndorf
 */
@ContextConfiguration(
        classes = {Cylus2.class, AcceptanceTestConfig.class},
        initializers = ConfigFileApplicationContextInitializer.class,
        loader = SpringBootContextLoader.class
)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractSchritte extends FluentAdapter {

    @Autowired
    WebDriver webDriver;

    @LocalServerPort
    private Integer port;

    @PostConstruct
    public void setup() {
        setBaseUrl("http://localhost:" + port + '/');
        initFluent(webDriver);
        inject(this);
    }

}

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

import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.WebClient;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
@Configuration
public class WebAppExecutingTestContext {

    @Value("${security.user.name}")
    private String login;


    @Value("${security.user.password}")
    private String password;

    @Bean
    public WebDriver webDriver() {
        return new HtmlUnitDriver() {
            @Override
            protected WebClient getWebClient() {
                final WebClient webClient = super.getWebClient();
                final DefaultCredentialsProvider credentialsProvider = new DefaultCredentialsProvider();
                credentialsProvider.addCredentials(login, password);
                webClient.setCredentialsProvider(credentialsProvider);

                return webClient;
            }

        };
    }

}

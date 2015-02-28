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

package net.cyphoria.cylus.acceptancetest.seiten;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.io.File;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
public class ImportSeite extends FluentPage {

    FluentWebElement importDatei;

    FluentWebElement starteImport;

    @Override
    public String getUrl() {
        return "/import";
    }

    @Override
    public void isAt() {
        assertThat("Title tag", title(), containsString("Kontoauszug Import"));
        assertThat("Ueberschrift",findFirst("h1#title").getText(), is("Import"));
    }

    public ImportSeite importiere(final File file) {
        assertThat(file, exists());
        importDatei.fill().with(file.getAbsolutePath());
        starteImport.submit();
        return this;
    }

    private static Matcher<? super File> exists() {
        return new TypeSafeMatcher<File>() {
            @Override
            protected boolean matchesSafely(final File item) {
                return item.exists();
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("the file exists");
            }
        };
    }
}

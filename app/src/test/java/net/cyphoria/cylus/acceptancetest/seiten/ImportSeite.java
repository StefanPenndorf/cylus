/*
 * Copyright (c) Stefan Penndorf 2016
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
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.TypeSafeMatcher;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Stefan Pennndorf
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

    public void hatBuchungen(final String... buchungstexte) {
        final FluentList<FluentWebElement> textZellen = find("#buchungsuebersicht tr td.buchungstext");

        assertThat(textZellen, hasItems(withTexts(buchungstexte)));
    }

    @SuppressWarnings("unchecked")
    private static Matcher<? super FluentWebElement>[] withTexts(final String[] buchungstexte) {
        return Arrays.stream(buchungstexte).map(
                WithTextMatcher::new
        ).collect(Collectors.toList()).toArray(new Matcher[buchungstexte.length]);
    }

    private static class WithTextMatcher extends TypeSafeDiagnosingMatcher<FluentWebElement> {
        private final String matchingText;

        private WithTextMatcher(final String matchingText) {
            this.matchingText = matchingText;
        }

        @Override
        protected boolean matchesSafely(final FluentWebElement item, final Description mismatchDescription) {
            mismatchDescription.appendText(" text was ").appendValue(item.getText());
            return item.getText().contains(matchingText);
        }

        @Override
        public void describeTo(final Description description) {
            description.appendText("an element with text ").appendValue(matchingText);
        }
    }
}

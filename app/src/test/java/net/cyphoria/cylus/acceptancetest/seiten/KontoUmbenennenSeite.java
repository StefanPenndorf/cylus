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
import org.hamcrest.Matchers;
import org.openqa.selenium.support.FindBy;

import java.util.Optional;

import static java.util.Optional.empty;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * @author Stefan Penndorf
 */
public class KontoUmbenennenSeite extends FluentPage {

    private Optional<Integer> kontoNummer = empty();

    @FindBy(css = "#kontoName")
    FluentWebElement kontoNameFeld;

    @FindBy(css = "button[type=submit]")
    FluentWebElement kontoUmbenennen;

    @Override
    public String getUrl() {
        return kontoNummer.map(k -> "/konto/umbenennen/" + k).get();
    }

    @Override
    public void isAt() {
        assertThat(kontoNummer, is(not(empty())));
        assertThat(title(), Matchers.allOf(
                containsString("Konto"),
                containsString(kontoNummer.get().toString()),
                containsString("umbenennen")
        ));
    }

    public void benenneKontoUmIn(final String neuerKontoName) {
        kontoNameFeld.fill().with(neuerKontoName);
        kontoUmbenennen.submit();
    }

    public KontoUmbenennenSeite für(final Integer kontoNr) {
        kontoNummer = Optional.of(kontoNr);
        return this;
    }
}

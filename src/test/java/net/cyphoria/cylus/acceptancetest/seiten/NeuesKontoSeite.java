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
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
public class NeuesKontoSeite extends FluentPage {

    @FindBy(css = "#kontoNummer")
    FluentWebElement kontoNummer;


    @FindBy(css = "#kontoName")
    FluentWebElement kontoName;

    @FindBy(css = "#kontoArt")
    FluentWebElement kontoArt;

    @FindBy(css = "button[type=submit]")
    FluentWebElement kontoAnlegen;


    @Override
    public String getUrl() {
        return "/konto/neu";
    }


    public NeuesKontoSeite legeKontoAn() {
        isAt();
        kontoNummer.fill().with("4101");
        kontoName.fill().with("Lebensmittel");
        kontoArt.fill().fillSelect("select").withText("Aufwand");
        kontoAnlegen.click();
        return this;
    }

    @Override
    public void isAt() {
        assertThat(title(), containsString("Neues Konto anlegen"));
    }
}

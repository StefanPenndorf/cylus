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

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.cyphoria.cylus.acceptancetest.seiten.Kontenplan;
import org.fluentlenium.core.annotation.Page;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
public class KontenplanSchritte extends AbstractSchritte {

    @Page
    private Kontenplan kontenplan;

    @Given("^ich habe den Kontenplan geöffnet$")
    public void ich_habe_den_Kontenplan_geöffnet() throws Throwable {
        goTo(kontenplan).await().untilPage();
    }

    @When("^ich ein neues Aufwands-Konto \"([0-9]+)\" \"([^\"]*)\" anlege$")
    public void ich_ein_neues_Aufwands_Konto_anlege(
            final Integer kontoNummer,
            final String kontoName) throws Throwable {
        kontenplan.legeNeuesKontoAn(kontoNummer, kontoName, "Aufwand");
    }

    @Then("^wird das Konto \"([0-9]+)\" \"([^\"]*)\" im Kontenplan angezeigt$")
    public void wird_das_Konto_im_Kontenplan_angezeigt(
            final Integer kontoNummer,
            final String kontoName) throws Throwable {
        kontenplan.zeigtKonto(kontoNummer, kontoName);
    }
}

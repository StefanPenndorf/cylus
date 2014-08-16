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

package net.cyphoria.cylus.service.konto;

import net.cyphoria.cylus.domain.validierung.Ergebnis;
import net.cyphoria.cylus.domain.validierung.Ergebnis.Fehlerhaft;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class KontoAnlageAnfrageTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private KontoAnlageAnfrage anfrage = new KontoAnlageAnfrage();

    @Test
    public void istGueltigWennAlleFelderGesetzt() {
        anfrage.setKontoName("Lebensmittel");

        assertThat(anfrage.validiere(), is(Ergebnis.gueltig()));
    }

    @Test
    public void ungueltigWennKeinKontoNameAngegeben() {
        assertThat(anfrage.validiere(), is(ungueltig()));
    }

    private static Matcher<Ergebnis> ungueltig() {
        return instanceOf(Fehlerhaft.class);
    }


}
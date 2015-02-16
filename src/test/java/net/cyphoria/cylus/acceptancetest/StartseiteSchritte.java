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

import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Und;
import cucumber.api.java.de.Wenn;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
public class StartseiteSchritte extends AbstractSchritte {

    @Angenommen("^die Anwendung ist gestartet$")
    public void die_Anwendung_ist_gestartet() throws Throwable {
        // nichts zu tun
    }

    @Wenn("^ich die erste Seite aufrufe$")
    public void ich_die_erste_Seite_aufrufe() throws Throwable {
        goTo(getBaseUrl());
    }

    @Dann("^wird der Titel \"([^\"]*)\" angezeigt$")
    public void wird_der_Titel_angezeigt(final String titel) throws Throwable {
        assertThat(title(), is(titel));
    }

    @Und("^wird die Überschrift \"([^\"]*)\" angezeigt$")
    public void wird_die_Überschrift_angezeigt(final String ueberschrift) throws Throwable {
        assertThat(findFirst("h1#title").getText(), is(ueberschrift));
    }
}

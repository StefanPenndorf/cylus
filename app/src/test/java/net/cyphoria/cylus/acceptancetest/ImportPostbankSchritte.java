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

package net.cyphoria.cylus.acceptancetest;

import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Wenn;
import net.cyphoria.cylus.acceptancetest.seiten.ImportSeite;
import org.fluentlenium.core.annotation.Page;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Stefan Pennndorf
 */
public class ImportPostbankSchritte extends AbstractSchritte {

    private static final File KONTOAUSZUG;

    static {
        try {
            final URL systemResource = ClassLoader.getSystemResource("net/cyphoria/cylus/fixtures/kontoauszug.csv");
            if(systemResource == null) {
                throw new IllegalStateException("Unable to resolve resource/Required resource not found.");
            }
            final URI uri = systemResource.toURI();
            KONTOAUSZUG = new File(uri);
        } catch (final URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    @Page
    ImportSeite importSeite;

    @Wenn("^ich einen Konto-Auszug importiere$")
    public void ich_einen_Konto_Auszug_importiere() throws Throwable {
        goTo(importSeite).isAt();
        importSeite.importiere(KONTOAUSZUG);
    }

    @Dann("^werden die importierten Buchungen angezeigt$")
    public void werdenDieImportiertenBuchungenAngezeigt() throws Throwable {
        importSeite.hatBuchungen("Buchungszeichen FOOBAR", "ELV61312344 07.05 10.54 ME2");
    }
}

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
import cucumber.api.java.de.Wenn;
import net.cyphoria.cylus.acceptancetest.seiten.Kontenplan;
import net.cyphoria.cylus.domain.KontenArt;
import net.cyphoria.cylus.domain.Konto;
import net.cyphoria.cylus.domain.repositories.KontenArtRepository;
import net.cyphoria.cylus.domain.repositories.KontoRepository;
import org.fluentlenium.core.annotation.Page;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
public class KontenplanSchritte extends AbstractSchritte {

    @Page
    private Kontenplan kontenplan;

    @Autowired
    private KontoRepository kontoRepository;

    @Autowired
    private KontenArtRepository kontenArtRepository;

    @Angenommen("^ich habe den Kontenplan geöffnet$")
    public void ich_habe_den_Kontenplan_geöffnet() throws Throwable {
        goTo(kontenplan).await().untilPage();
    }

    @Angenommen("^das Konto \"([^\"]*)\" \"([^\"]*)\" wurde im Kontenplan angelegt$")
    public void das_Konto_wurde_im_Kontenplan_angelegt(
            final Integer kontoNummer,
            final String kontoName) throws Throwable {
        kontoRepository.deleteAll();

        final KontenArt aufwand = kontenArtRepository.findByName("Aufwand");
        final Konto konto = new Konto(kontoNummer, kontoName, aufwand);

        kontoRepository.save(konto);

        goTo(kontenplan).await().untilPage();
    }

    @Wenn("^ich ein neues Aufwands-Konto \"([0-9]+)\" \"([^\"]*)\" anlege$")
    public void ich_ein_neues_Aufwands_Konto_anlege(
            final Integer kontoNummer,
            final String kontoName) throws Throwable {
        kontenplan.legeNeuesKontoAn(kontoNummer, kontoName, "Aufwand");
    }

    @Wenn("^ich das Konto \"([^\"]*)\" in \"([^\"]*)\" umbenenne$")
    public void ich_das_Konto_in_umbenenne(
            final Integer kontoNummer,
            final String neuerKontoName) throws Throwable {
        kontenplan.benenneKontoUm(kontoNummer, neuerKontoName);
    }

    @Dann("^wird das Konto \"([0-9]+)\" \"([^\"]*)\" im Kontenplan angezeigt$")
    public void wird_das_Konto_im_Kontenplan_angezeigt(
            final Integer kontoNummer,
            final String kontoName) throws Throwable {
        LoggerFactory.getLogger(getClass()).error(pageSource());
        kontenplan.zeigtKonto(kontoNummer, kontoName);
    }
}

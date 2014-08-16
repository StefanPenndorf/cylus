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

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
public class KontoAnlageAnfrage {

    private String kontoName;
    private String kontoNummer;
    private String kontoArt;


    public Ergebnis validiere() {
        return kontoName == null ? Ergebnis.fehlerhaft() : Ergebnis.gueltig();
    }

    public Integer getKontoNummer() {
        return Integer.valueOf(kontoNummer);
    }

    public String getKontoName() {
        return kontoName;
    }

    public void setKontoName(final String kontoName) {
        this.kontoName = kontoName;
    }

    public void setKontoNummer(final String kontoNummer) {
        this.kontoNummer = kontoNummer;
    }

    public String getKontoArt() {
        return kontoArt;
    }

    public void setKontoArt(final String kontoArt) {
        this.kontoArt = kontoArt;
    }
}

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

import net.cyphoria.cylus.domain.KontenArt;
import net.cyphoria.cylus.domain.Kontenplan;

import java.util.List;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
public interface KontoService {

    void legeNeuesKontoAn(KontoAnlageAnfrage anfrage);

    List<KontenArt> getListeDerKontenArten();


    Kontenplan ladeKontenplan();
}

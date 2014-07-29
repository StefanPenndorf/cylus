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
        goTo(kontenplan).isAt();
    }
}

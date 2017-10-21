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

package net.cyphoria.cylus.acceptancetest.schritte;

import cucumber.api.java.de.Wenn;
import net.cyphoria.cylus.acceptancetest.seiten.ImportSeite;
import org.fluentlenium.core.annotation.Page;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Stefan Pennndorf
 */
public class ImportSchritte extends AbstractSchritte {

    private static final Path DKB_EXPORT_CSV_DATEI = Paths.get("foo.csv");
    @Page
    public ImportSeite importSeite;

    @Wenn("^ich einen DKB-Export importiere$")
    public void ichEinenDKBExportImportiere() throws Throwable {
        importSeite.go();
        importSeite.importiere(DKB_EXPORT_CSV_DATEI);
    }
}

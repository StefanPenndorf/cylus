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
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import java.nio.file.Path;

/**
 * @author Stefan Pennndorf
 */
@PageUrl("/import/journal/dkb")
public class ImportSeite extends FluentPage {

    @FindBy(css = "#buchungsjournal-datei")
    FluentWebElement buchungsjournalDateiFeld;

    @FindBy(css = "#import-starten")
    FluentWebElement importStartenButton;



    public void importiere(final Path dkbExportCsvDatei) {
        buchungsjournalDateiFeld.fill().withText(dkbExportCsvDatei.toAbsolutePath().toString());
        importStartenButton.click();
    }
}

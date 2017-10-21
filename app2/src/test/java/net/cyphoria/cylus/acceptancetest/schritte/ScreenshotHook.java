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

import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Stefan Pennndorf
 */
public class ScreenshotHook extends AbstractSchritte {

    private static final File SCREENSHOT_DIR = Paths.get("target/screenshots/").toAbsolutePath().toFile();
    public static final String PNG_FILE_ENDING = ".png";
    private static final Logger LOGGER = LoggerFactory.getLogger(ScreenshotHook.class);

    @After
    public void takeScreenshot(final Scenario scenario) {

        if (scenario.isFailed() && webDriver instanceof TakesScreenshot) {
            final String scenarioName = scenario.getName();
            takeScreenshot(scenarioName, (TakesScreenshot) webDriver);
        }
    }

    private static void takeScreenshot(final String scenarioName, final TakesScreenshot screenshotTaker) {
        createScreenshotDirectoryIfNotExists();

        final String fileName = String.format("%s%s", scenarioName, PNG_FILE_ENDING);
        final File screenshotFile = new File(SCREENSHOT_DIR, fileName);
        final boolean renameSuccessfull = screenshotTaker.getScreenshotAs(OutputType.FILE).renameTo(screenshotFile);
        if (!renameSuccessfull) {
            LOGGER.warn("Fehler beim speichern der Screenshot-Datei {}", screenshotFile);
        }
    }

    private static void createScreenshotDirectoryIfNotExists() {
        if (!SCREENSHOT_DIR.exists()) {
            try {
                Files.createDirectories(SCREENSHOT_DIR.toPath());
            } catch (final IOException e) {
                throw new UncheckedIOException("Kann das Screenshot-Verzeichnis nicht anlegen: " + SCREENSHOT_DIR.getAbsolutePath(), e);
            }
        }
    }

}

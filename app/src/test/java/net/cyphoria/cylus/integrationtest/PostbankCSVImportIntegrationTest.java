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

package net.cyphoria.cylus.integrationtest;

import net.cyphoria.cylus.buchungsimport.CsvImportService;
import net.cyphoria.cylus.buchungsimport.UploadRepository;
import net.cyphoria.cylus.buchungsimport.UploadToDiskRepository;
import net.cyphoria.cylus.buchungsimport.UploadedFileKey;
import net.cyphoria.cylus.domain.BuchungsImportLauf;
import net.cyphoria.cylus.domain.repositories.BuchungsImportLaufRepository;
import net.cyphoria.cylus.infrastructure.BatchConfiguration;
import net.cyphoria.cylus.infrastructure.CSVFileJobLauncher;
import net.cyphoria.cylus.infrastructure.CylusNeo4jConfiguration;
import net.cyphoria.cylus.infrastructure.GraphDatabaseConfiguration;
import net.cyphoria.cylus.infrastructure.IntegrationConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Stefan Pennndorf
 */
@IntegrationTest
@SpringApplicationConfiguration(classes = {
        IntegrationTestConfiguration.class,
        IntegrationConfiguration.class,
        BatchConfiguration.class,
        CsvImportService.class,
        CSVFileJobLauncher.class,
        UploadToDiskRepository.class,
        CylusNeo4jConfiguration.class,
        GraphDatabaseConfiguration.class,

})
@ActiveProfiles("graph-store")
@RunWith(SpringJUnit4ClassRunner.class)
public class PostbankCSVImportIntegrationTest {

    @Autowired
    CsvImportService importService;

    @Autowired
    UploadRepository uploadRepository;

    @Autowired
    BuchungsImportLaufRepository importLaufRepository;

    @Test
    public void verarbeitetEineBuchung() throws IOException {
        final ClassPathResource file = new ClassPathResource("net/cyphoria/cylus/fixtures/kontoauszug_1buchung.csv");

        final UploadedFileKey upload = uploadRepository.save(file);

        importService.importCSVFile(upload);

        assertThat(importLaufRepository.count(), is(1));

        BuchungsImportLauf lauf = importLaufRepository.findAll().iterator().next();

        assertThat(lauf.getAnzahlImportierteBuchungen(), is(1));
    }

}

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

package net.cyphoria.cylus.web.controller;

import net.cyphoria.cylus.testsupport.MockitoRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Stefan Pennndorf
 */
public class ImportControllerTest {

    private static final MultipartFile CSV_DATEI = new MockMultipartFile("foo", "Foo;fbaararjkj;".getBytes());

    @Rule
    public final MockitoRule mockitoRule = new MockitoRule();

    private ImportController controller;

    @Before
    public void setup() {
        controller = new ImportController();
    }

    @Test
    public void zeigtUploadFormular() {
        assertThat(controller.uploadFormular(), is("import/upload"));
    }

    @Test
    public void erfolgreicherUploadZeigtBuchungsübersicht() {
        assertThat(controller.verarbeiteUpload(CSV_DATEI), is("import/buchungen"));
    }

}
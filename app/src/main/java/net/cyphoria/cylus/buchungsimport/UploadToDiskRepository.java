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

package net.cyphoria.cylus.buchungsimport;

import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author Stefan Pennndorf
 */
@Repository
public class UploadToDiskRepository implements UploadRepository {


    @Override
    public UploadedFileKey save(final InputStreamSource uploadContents) throws IOException {
        final Path tempFile = Files.createTempFile("upload", ".csv");

        try (final InputStream in = uploadContents.getInputStream()){
            Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING);
        }

        return new UploadedFileKey(tempFile);
    }

}

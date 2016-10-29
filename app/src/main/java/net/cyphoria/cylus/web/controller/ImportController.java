/*
 * Copyright (c) Stefan Penndorf 2016
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

import net.cyphoria.cylus.buchungsimport.CsvImportService;
import net.cyphoria.cylus.buchungsimport.UploadRepository;
import net.cyphoria.cylus.buchungsimport.UploadedFileKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Stefan Pennndorf
 */
@Controller
@RequestMapping("/import")
public class ImportController {

    @Autowired
    UploadRepository uploadRepository;

    @Autowired
    CsvImportService importService;

    @RequestMapping(method = RequestMethod.GET)
    public String uploadFormular() {
        return "import/upload";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String verarbeiteUpload(@RequestParam("importDatei") final MultipartFile file) throws IOException {
//        final String name = file.getName();
        if (file.isEmpty()) {
            return "import/upload";
        }

        final UploadedFileKey upload = uploadRepository.save(file);

        importService.importCSVFile(upload);

//        csvFileInputChannel.send(new GenericMessage<Object>(new CSVFile(file)));
        return "import/buchungen";
//            try {
//                final byte[] bytes = file.getBytes();
//                final String content = new String(bytes, "UTF-8");
//                return "You successfully uploaded " + name + "!\nContent: " + content ;
//            } catch (final Exception e) {
//                return "You failed to upload " + name + " => " + e.getMessage();
//            }
//        } else {
//            return "You failed to upload " + name + " because the file was empty.";
//        }
    }

}

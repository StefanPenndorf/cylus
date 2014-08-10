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

package net.cyphoria.cylus.controller;

import net.cyphoria.cylus.domain.KontenArt;
import net.cyphoria.cylus.forms.KontoForm;
import net.cyphoria.cylus.repositories.KontenArtRepository;
import net.cyphoria.cylus.repositories.KontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
@Controller
@RequestMapping("/konto")
@Transactional(readOnly =  true)
public class KontoController {

    private final KontenArtRepository kontoArtenRepository;
    private final KontoRepository kontoRepository;

    @Autowired
    public KontoController(final KontenArtRepository kontoArtenRepository, final KontoRepository kontoRepository) {
        this.kontoArtenRepository = kontoArtenRepository;
        this.kontoRepository = kontoRepository;
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/neu", method = GET)
    public String neuesKonto(final Model model) {
        final List<KontenArt> alleKontenArten = kontoArtenRepository.findAll().as(List.class);

        model.addAttribute("kontenArten", alleKontenArten);

        return "konto/neu";
    }

    @RequestMapping(value = "/neu", method = POST)
    @Transactional(readOnly = false)
    public String speichereNeuesKonto(@ModelAttribute final KontoForm kontoForm) {
        kontoRepository.save(kontoForm.toKonto());
        return "redirect:/kontenplan";
    }
}

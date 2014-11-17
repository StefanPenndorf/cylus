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

import net.cyphoria.cylus.domain.KontenArt;
import net.cyphoria.cylus.domain.Konto;
import net.cyphoria.cylus.service.konto.KontoAnlageAnfrage;
import net.cyphoria.cylus.service.konto.KontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
@Controller
@RequestMapping("/konto")
@Transactional(readOnly =  true)
public class KontoController {

    private final KontoService kontoService;

    @Autowired
    public KontoController(final KontoService kontoService) {
        this.kontoService = kontoService;
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/neu", method = GET)
    public String neuesKonto(final Model model) {
        final Iterable<KontenArt> alleKontenArten = kontoService.getListeDerKontenArten();

        model.addAttribute("kontenArten", alleKontenArten);

        return "konto/neu";
    }

    @RequestMapping(value = "/umbenennen/{kontoNummer}", method = GET)
    public String kontoUmbenennen(
            @PathVariable final Integer kontoNummer,
                          final Model model) throws ResourceNotFoundException {

        final Konto konto = kontoService
                .findeKontoMitKontoNummer(kontoNummer)
                .orElseThrow(ResourceNotFoundException::new);

        model.addAttribute("konto", konto);

        return "konto/umbenennen";
    }

    @RequestMapping(value = "/neu", method = POST)
    @Transactional(readOnly = false)
    public String speichereNeuesKonto(@ModelAttribute final KontoAnlageAnfrage anfrage) {
        kontoService.legeNeuesKontoAn(anfrage);
        return "redirect:/kontenplan";
    }
}

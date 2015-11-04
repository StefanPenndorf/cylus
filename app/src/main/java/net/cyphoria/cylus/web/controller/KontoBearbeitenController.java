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

import net.cyphoria.cylus.domain.Konto;
import net.cyphoria.cylus.service.konto.KontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Stefan Penndorf
 */
@Controller
@RequestMapping("/konto/umbenennen")
public class KontoBearbeitenController {

    private final KontoService kontoService;

    @Autowired
    public KontoBearbeitenController(final KontoService kontoService) {
        this.kontoService = kontoService;
    }

    @InitBinder
    private void initBinder(final WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }

    @ModelAttribute
    public Konto getKonto(@PathVariable("kontoNummer") final Integer kontoNummer) throws ResourceNotFoundException {
        return kontoService
                .findeKontoMitKontoNummer(kontoNummer)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping(value = "/{kontoNummer}", method = GET)
    public String kontoUmbenennen() {
        return "konto/umbenennen";
    }

    @RequestMapping(value = "/{kontoNummer}", method = POST)
    public String speichereNeuenNamen(
            @ModelAttribute @Valid final Konto konto,
            final BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            return "konto/umbenennen";
        }

        kontoService.benenneKontoUm(konto);
        return "redirect:/kontenplan";
    }

}

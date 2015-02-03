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
import net.cyphoria.cylus.service.konto.KontoService;
import net.cyphoria.cylus.testsupport.MockitoRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KontoBearbeitenControllerTest {

    private static final KontenArt KONTEN_ART = new KontenArt("Test");
    private static final Integer KONTO_NUMMER = 11;
    private static final Konto KONTO = new Konto(KONTO_NUMMER, "", KONTEN_ART);
    private static final Konto IGNORED_KONTO = KONTO;

    @Rule
    public final MockitoRule mockito = new MockitoRule();

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Mock
    private KontoService kontoService;

    @Mock
    private BindingResult bindingResult;

    private KontoBearbeitenController controller;

    @Before
    public void setup() {
        controller = new KontoBearbeitenController(kontoService);
    }

    @Test
    public void kontoModellObjektWirdÜberKontoNummerGeladen() throws ResourceNotFoundException {
        when(kontoService.findeKontoMitKontoNummer(KONTO_NUMMER)).thenReturn(Optional.of(KONTO));

        assertThat(controller.getKonto(KONTO_NUMMER), is(KONTO));
    }

    @Test
    public void wirftResourceNotFoundWennKontoNummerUngueltig() throws ResourceNotFoundException {
        when(kontoService.findeKontoMitKontoNummer(KONTO_NUMMER)).thenReturn(Optional.empty());

        expectedException.expect(ResourceNotFoundException.class);

        controller.getKonto(KONTO_NUMMER);
    }


    @Test
    public void kontoUmbenennenZeigtDasUmbenennenTemplate() throws ResourceNotFoundException {
        assertThat(controller.kontoUmbenennen(), is("konto/umbenennen"));
    }


    @Test
    public void postSpeichereNeuenNamenLeitetBeiErfolgZumKontenplan() throws BindException {
        when(bindingResult.hasErrors()).thenReturn(false);

        final String result = controller.speichereNeuenNamen(KONTO, bindingResult);

        assertThat(result, is("redirect:/kontenplan"));
    }

    @Test
    public void postSpeichereNeuenNamenÄndertDenNamenDesKontos() throws BindException {
        when(bindingResult.hasErrors()).thenReturn(false);

        controller.speichereNeuenNamen(KONTO, bindingResult);

        verify(kontoService).benenneKontoUm(KONTO);
    }

    @Test
    public void postSpeichereNeuenNamenZeigtDasFormularBeiEingabefehlern() throws BindException {
        when(bindingResult.hasErrors()).thenReturn(true);

        final String result = controller.speichereNeuenNamen(IGNORED_KONTO, bindingResult);

        assertThat(result, is("konto/umbenennen"));
    }

}
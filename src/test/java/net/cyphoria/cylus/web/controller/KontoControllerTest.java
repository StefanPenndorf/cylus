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
import net.cyphoria.cylus.service.konto.KontoAnlageAnfrage;
import net.cyphoria.cylus.service.konto.KontoService;
import net.cyphoria.cylus.testsupport.MockitoRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KontoControllerTest {

    private static final KontenArt KONTEN_ART = new KontenArt("Test");
    private static final List<KontenArt> KONTEN_ARTEN = singletonList(KONTEN_ART);

    @Rule
    public final MockitoRule mockito = new MockitoRule();

    private final Model model = new ExtendedModelMap();

    @Mock
    private KontoService kontoService;

    private KontoController controller;

    @Before
    public void setup() {
        controller = new KontoController(kontoService);
    }

    @Test
    public void getNeuesKontozeigtDasNeuTemplateAn() {
        final String template = controller.neuesKonto(model);

        assertThat(template, is("konto/neu"));
    }

    @Test
    public void getNeuesKontosetztDieKontoArtenImModell() {
        when(kontoService.getListeDerKontenArten()).thenReturn(KONTEN_ARTEN);

        controller.neuesKonto(model);

        assertThat(model.asMap(), hasEntry("kontenArten", singletonList(KONTEN_ART)));
    }

    @Test
    public void postSpeichereNeuesKontoLegtEinNeuesKontoAn() {
        final KontoAnlageAnfrage anfrage = new KontoAnlageAnfrage();

        controller.speichereNeuesKonto(anfrage);

        verify(kontoService).legeNeuesKontoAn(anfrage);
    }

    @Test
    public void postSpeichereNeuesKontoLeitetBeiErfolgZumKontenplan() {
        final KontoAnlageAnfrage anfrage = new KontoAnlageAnfrage();

        final String result = controller.speichereNeuesKonto(anfrage);

        assertThat(result, is("redirect:/kontenplan"));
    }

}

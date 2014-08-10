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
import net.cyphoria.cylus.domain.Konto;
import net.cyphoria.cylus.forms.KontoForm;
import net.cyphoria.cylus.repositories.KontenArtRepository;
import net.cyphoria.cylus.repositories.KontoRepository;
import net.cyphoria.cylus.testsupport.MockitoRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.neo4j.conversion.QueryResultBuilder;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KontoControllerTest {

    public static final KontenArt KONTEN_ART = new KontenArt("Test");
    public static final QueryResultBuilder<KontenArt> RESULT_BUILDER =
            new QueryResultBuilder<>(singletonList(KONTEN_ART));

    @Rule
    public final MockitoRule mockito = new MockitoRule();

    private Model model = new ExtendedModelMap();

    @Mock
    private KontenArtRepository kontoArtenRepository;

    @Mock
    private KontoRepository kontoRepository;

    private KontoController controller;

    @Before
    public void setup() {
        controller = new KontoController(kontoArtenRepository, kontoRepository);
    }

    @Test
    public void getNeuesKontozeigtDasNeuTemplateAn() {
        when(kontoArtenRepository.findAll()).thenReturn(RESULT_BUILDER);

        final String template = controller.neuesKonto(model);

        assertThat(template, is("konto/neu"));
    }

    @Test
    public void getNeuesKontosetztDieKontoArtenImModell() {
        when(kontoArtenRepository.findAll()).thenReturn(RESULT_BUILDER);

        controller.neuesKonto(model);

        assertThat(model.asMap(), hasEntry("kontenArten", singletonList(KONTEN_ART)));
    }

    @Test
    public void postSpeichereNeuesKontoSpeichertDasKontoImRepository() {
        final KontenArt kontenArt = new KontenArt("Aufwand");
        final Konto konto = new Konto(4001, "Lebensmittel", kontenArt);
        final KontoForm form = new KontoForm()
                .withKontoNummer(4001)
                .withKontoName("Lebensmittel")
                .withKontenArt("Aufwand");

        controller.speichereNeuesKonto(form);

        verify(kontoRepository).save((Konto)argThat(allOf(hasProperty("kontenArt", is(kontenArt)), is(konto))));
    }

    @Test
    public void postSpeichereNeuesKontoLeitetBeiErfolgZumKontenplan() {
        final KontoForm form = new KontoForm().withKontoNummer(0);

        final String result = controller.speichereNeuesKonto(form);

        assertThat(result, is("redirect:/kontenplan"));
    }


}
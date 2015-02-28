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

import net.cyphoria.cylus.domain.Kontenplan;
import net.cyphoria.cylus.service.konto.KontoService;
import net.cyphoria.cylus.testsupport.MockitoRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class KontenplanControllerTest {

    private static final Kontenplan KONTENPLAN = new Kontenplan(emptyList());
    KontenplanController controller;

    @Rule
    public final MockitoRule mockitoRule = new MockitoRule();

    @Mock
    private KontoService kontoService;
    private final Model model = new ExtendedModelMap();


    @Before
    public void setup() {
        controller = new KontenplanController(kontoService);
    }

    @Test
    public void zeigeKontenplanVerwendetKontenplanTemplate() {
        assertThat(controller.zeigeKontenplan(model), is("kontenplan"));
    }

    @Test
    public void zeigeKontenplanZeigtDenKontenplan() {
        when(kontoService.ladeKontenplan()).thenReturn(KONTENPLAN);

        controller.zeigeKontenplan(model);

        assertThat("Model has kontenplan", model.asMap().get("kontenplan"), is(KONTENPLAN));
    }

}
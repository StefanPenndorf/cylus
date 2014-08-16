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

package net.cyphoria.cylus.service.konto;

import net.cyphoria.cylus.domain.KontenArt;
import net.cyphoria.cylus.domain.Konto;
import net.cyphoria.cylus.domain.repositories.KontenArtRepository;
import net.cyphoria.cylus.domain.repositories.KontoRepository;
import net.cyphoria.cylus.testsupport.MockitoRule;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Objects;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DefaultKontoServiceTest {


    private static final String KONTO_NAME = "Lebensmittel";
    private static final Integer KONTO_NUMMER = 4001;
    private static final KontenArt KONTO_ART = new KontenArt("Aufwand");
    @Rule
    public final MockitoRule mockito = new MockitoRule();

    @Mock
    private KontoRepository kontoRepository;

    @Mock
    private KontenArtRepository kontenArtRepository;

    private DefaultKontoService service;

    @Before
    public void setup() {
        this.service = new DefaultKontoService(kontoRepository, kontenArtRepository);
    }

    @Test
    public void legeNeuesKontoAnSpeichertDasNeueKonto() {
        final KontoAnlageAnfrage anfrage = new KontoAnlageAnfrage();
        anfrage.setKontoName(KONTO_NAME);
        anfrage.setKontoNummer(KONTO_NUMMER.toString());
        anfrage.setKontoArt(KONTO_ART.name);

        final Konto konto = new Konto(KONTO_NUMMER, KONTO_NAME, KONTO_ART);

        service.legeNeuesKontoAn(anfrage);

        verify(kontoRepository).save(konto);
    }

    @Test
    public void legeNeuesKontoAnLegtDasKontoFuerDieRichtigeKontoArtAn() {
        final KontoAnlageAnfrage anfrage = new KontoAnlageAnfrage();
        anfrage.setKontoName(KONTO_NAME);
        anfrage.setKontoNummer(KONTO_NUMMER.toString());
        anfrage.setKontoArt(KONTO_ART.name);

        when(kontenArtRepository.findByName(KONTO_ART.name)).thenReturn(KONTO_ART);

        service.legeNeuesKontoAn(anfrage);

        verify(kontoRepository).save(kontoMitKontenArt(KONTO_ART));
    }

    private static Konto kontoMitKontenArt(final KontenArt kontoArt) {
        return argThat(new TypeSafeMatcher<Konto>() {
            @Override
            public void describeTo(final Description description) {
                description.appendText("Konto mit KontenArt ").appendValue(kontoArt);
            }

            @Override
            protected boolean matchesSafely(final Konto item) {
                return Objects.equals(item.getKontenArt(), kontoArt);
            }

            @Override
            protected void describeMismatchSafely(final Konto item, final Description mismatchDescription) {
                mismatchDescription.appendText("Konto mit KontenArt ").appendValue(item.getKontenArt());
            }
        });
    }

}
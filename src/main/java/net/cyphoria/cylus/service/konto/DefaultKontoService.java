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

import com.google.common.collect.ImmutableList;
import net.cyphoria.cylus.domain.KontenArt;
import net.cyphoria.cylus.domain.Kontenplan;
import net.cyphoria.cylus.domain.Konto;
import net.cyphoria.cylus.domain.repositories.KontenArtRepository;
import net.cyphoria.cylus.domain.repositories.KontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
@Service
@Transactional(readOnly = true)
public class DefaultKontoService implements KontoService {

    private final KontoRepository kontoRepository;
    private final KontenArtRepository kontenArtRepository;

    @Autowired
    public DefaultKontoService(final KontoRepository kontoRepository, final KontenArtRepository kontenArtRepository) {
        this.kontoRepository = kontoRepository;
        this.kontenArtRepository = kontenArtRepository;
    }

    @Transactional(readOnly = false)
    @Override
    public void legeNeuesKontoAn(final KontoAnlageAnfrage anfrage) {
        final KontenArt kontenArt = kontenArtRepository.findByName(anfrage.getKontoArt());
        final Konto neuesKonto = new Konto(anfrage.getKontoNummer(), anfrage.getKontoName(), kontenArt);

        kontoRepository.save(neuesKonto);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<KontenArt> getListeDerKontenArten() {
        return ImmutableList.copyOf(kontenArtRepository.findAll());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Kontenplan ladeKontenplan() {
        final Iterable<Konto> kontoIterator = kontoRepository.findAll();

        return new Kontenplan(kontoIterator);
    }

    @Override
    public Optional<Konto> findeKontoMitKontoNummer(final Integer kontoNummer) {
        return Optional.ofNullable(kontoRepository.findByKontoNummer(kontoNummer));
    }

    @Transactional(readOnly = false)
    @Override
    public void benenneKontoUm(final Integer kontoNummer, final String neuerKontoName) {
        final Konto konto = kontoRepository.findByKontoNummer(kontoNummer);
        konto.renameTo(neuerKontoName);
        kontoRepository.save(konto);
    }


}

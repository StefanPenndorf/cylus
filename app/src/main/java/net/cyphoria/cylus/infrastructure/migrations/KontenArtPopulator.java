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

package net.cyphoria.cylus.infrastructure.migrations;

import net.cyphoria.cylus.domain.KontenArt;
import net.cyphoria.cylus.domain.repositories.KontenArtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * @author Stefan Penndorf
 */
@Component
@Order(1)
public class KontenArtPopulator implements Migration {

    @Autowired
    private KontenArtRepository repository;

    private static final List<KontenArt> KONTEN_ARTEN =
            asList(
                    "Aktiva",
                    "Passiva",
                    "Ertrag",
                    "Aufwand",
                    "Sonderkonten")
            .stream().map(KontenArt::new).collect(toList());


    @Override
    @Transactional
    public void migrate() {
        KONTEN_ARTEN.stream()
                .filter(k -> repository.findByName(k.name) == null)
                .forEach(repository::save);
    }
}

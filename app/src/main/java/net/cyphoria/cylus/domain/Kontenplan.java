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

package net.cyphoria.cylus.domain;



import com.google.common.collect.ImmutableSet;
import org.springframework.core.style.ToStringCreator;

import java.util.Collection;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
public class Kontenplan {
    private final ImmutableSet<Konto> konten;

    public Kontenplan(final Iterable<Konto> kontoListe) {
        konten = ImmutableSet.copyOf(kontoListe);
    }

    public Collection<Konto> getAlleKonten() {
        return konten;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this).append("konten", konten).toString();
    }
}

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

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
@NodeEntity
public class KontenArt implements Serializable {

    private static final long serialVersionUID = 2782423484039643855L;

    @GraphId private Long id;

    @Indexed public String name;

    public KontenArt() {}

    public KontenArt(final String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("KontenArt benötigt einen Namen! War: <" + name + '>');
        }
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final KontenArt kontenArt = (KontenArt) o;

        return Objects.equals(name, kontenArt.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "KontenArt{name='" + name + "'}";
    }
}

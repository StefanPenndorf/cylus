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
import org.springframework.data.neo4j.annotation.RelatedTo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
@NodeEntity
@Entity
public class Konto {

    @GraphId @Id @GeneratedValue(strategy = IDENTITY) private Long id;

    @Indexed private Integer kontoNummer;

    private String kontoName;

    @RelatedTo(type = "gehört zu")
    @ManyToOne
    private KontenArt kontenArt;

    public Konto() { }

    public Konto(final Integer kontoNummer, final String kontoName, final KontenArt kontenArt) {
        this.kontoNummer = kontoNummer;
        this.kontoName = kontoName;
        this.kontenArt = kontenArt;
    }

    @Override
    public String toString() {
        return "Konto{" +
                "id=" + id +
                ", kontoNummer=" + kontoNummer +
                ", kontoName='" + kontoName + '\'' +
                ", kontenArt=" + kontenArt +
                '}';
    }

    public KontenArt getKontenArt() {
        return kontenArt;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Konto konto = (Konto) o;

        return kontoName.equals(konto.kontoName) && kontoNummer.equals(konto.kontoNummer);

    }

    @Override
    public int hashCode() {
        int result = kontoNummer.hashCode();
        result = 31 * result + kontoName.hashCode();
        return result;
    }

    public String getName() {
        return kontoName;
    }

    public Integer getNummer() {
        return kontoNummer;
    }
}

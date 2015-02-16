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

package net.cyphoria.cylus.infrastructure;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Konfiguration für eine Neo4j Graph-Datenbank statt einer relationalen Datenbank.
 *
 * Benutzt aktuell eine statische DB zum Testen - das ist aktuell (nur) ein Hack damit
 * das Refreshen des Application Contexts bei den Akzeptanztests nicht zu Lock-Problemen
 * führt.
 * Kann zum schnellen Ausprobieren der Anwendung mit Graph-DB genutzt werden, ist aber so
 * nicht für den produktiven Einsatz geeignet (da sollte die Graph-DB sowieso von außen
 * bereitgestellt werden.
 *
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
@Profile("graph-store")
@Configuration
public class GraphDatabaseConfiguration {

    private static final String DATABASE_PATH = "target/cylus.db";

    private static final GraphDatabaseService GRAPH_DATABASE_SERVICE = new GraphDatabaseFactory().newEmbeddedDatabase(DATABASE_PATH);

    @Bean
    GraphDatabaseService graphDatabaseService() {
        return GRAPH_DATABASE_SERVICE;
    }


}

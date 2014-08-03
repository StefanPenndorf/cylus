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

package net.cyphoria.cylus;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Stefan Penndoorf <stefan@cyphoria.net>
 */
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan
public class Cylus {

    @Autowired
    private Environment environment;

    @Bean
    GraphDatabaseService graphDatabaseService() {
        final String path = environment.getProperty("cylus.neo4j.db.path", "cylus.db");
        return new GraphDatabaseFactory().newEmbeddedDatabase(path);
    }

    public static void main(final String[] args) {
        SpringApplication.run(Cylus.class, args);
    }

}

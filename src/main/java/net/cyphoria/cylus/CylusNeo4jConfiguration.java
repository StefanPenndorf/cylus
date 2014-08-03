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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "net.cyphoria.cylus.repositories")
public class CylusNeo4jConfiguration extends Neo4jConfiguration {

    @Autowired
    private Environment environment;

    public CylusNeo4jConfiguration() {
        setBasePackage("net.cyphoria.cylus.domain");
    }

    @Bean
    public GraphDatabaseService graphDatabaseService() {
        final String path = environment.getProperty("cylus.neo4j.db.path", "cylus.db");
        return new GraphDatabaseFactory().newEmbeddedDatabase(path);
    }


}
